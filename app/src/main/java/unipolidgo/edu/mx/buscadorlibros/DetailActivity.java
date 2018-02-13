package unipolidgo.edu.mx.buscadorlibros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DetailActivity extends AppCompatActivity {

    private static final String BASE_URL_CUBIERTA = "http://covers.openlibrary.org/b/id/";
    String cubiertaURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView cubierta = (ImageView) findViewById(R.id.cubierta);
        TextView autor = (TextView) findViewById(R.id.textAutor);
        TextView libro = (TextView) findViewById(R.id.textLibro);
        TextView fecha = (TextView) findViewById(R.id.textAno);
        TextView publicacion = (TextView) findViewById(R.id.textPublicado);

        String cubiertaID = this.getIntent().getExtras().getString("cubiertaID");
        String autorLibro = this.getIntent().getExtras().getString("autorLibro");
        String tituloLibro = this.getIntent().getExtras().getString("tituloLibro");
        String anoLibro = this.getIntent().getExtras().getString("anoLibro");
        String casaAutora = this.getIntent().getExtras().getString("casaAutora");

        if(cubiertaID.length() > 0){
            cubiertaURL = BASE_URL_CUBIERTA + cubiertaID + "-L.jpg";
            Picasso.with(this).load(cubiertaURL)
                    .placeholder(R.mipmap.ic_wait)
                    .into(cubierta);
        }

        autor.append(autorLibro);
        libro.append(tituloLibro);
        fecha.append(anoLibro);
        publicacion.append(casaAutora);

    }
}
