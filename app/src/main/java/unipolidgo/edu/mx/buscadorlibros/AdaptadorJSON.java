package unipolidgo.edu.mx.buscadorlibros;

/**
 * Created by jeobal on 08/02/2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class AdaptadorJSON extends BaseAdapter {
    private static final String url_base_img ="http://covers.openlibrary.org/b/id/";

    Context contexto;
    LayoutInflater vInflater;
    JSONArray json;

    public void updateData(JSONArray arreglo){
        json = arreglo;
        notifyDataSetChanged();
    }

    public AdaptadorJSON(Context context, LayoutInflater inflater){
        contexto = context;
        vInflater =inflater;
        json  = new JSONArray();
    }

    @Override
    public int getCount() {
        return json.length();
    }

    @Override
    public Object getItem(int i) {
        return json.optJSONObject(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null){
            view = vInflater.inflate(R.layout.fila, null);

            holder = new ViewHolder();
            holder.imagenView = (ImageView) view.findViewById(R.id.miniatura);
            holder.autorView = (TextView) view.findViewById(R.id.autor);
            holder.tituloView = (TextView) view.findViewById(R.id.titulo);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        JSONObject objeto = (JSONObject) getItem(i);
        if(objeto.has("cover_i")){
            String imagenID = objeto.optString("cover_i");
            String imagenURL = url_base_img + imagenID + "-S.jpg";

            Picasso.with(contexto)
            .load(imagenURL)
                    .placeholder(R.mipmap.ic_placeholder)
                    .into(holder.imagenView);
        }
        else{
            holder.imagenView.setImageResource(R.mipmap.ic_placeholder);
        }

        String libroTitulo="";
        String libroAutor = "";
        if(objeto.has("title")){
            libroTitulo = objeto.optString("title");
        }
        if (objeto.has("author_name")){
            libroAutor = objeto.optString("author_name");
        }

        holder.tituloView.setText(libroTitulo);
        holder.autorView.setText(libroAutor);
        return view;
    }

    private static class ViewHolder{
        public ImageView imagenView;
        public TextView tituloView;
        public TextView autorView;
    }
}
