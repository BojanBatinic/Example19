package rs.aleph.android.example16.fragments;

import android.app.Fragment;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import rs.aleph.android.example16.R;
import rs.aleph.android.example16.provider.ProviderCategory;
import rs.aleph.android.example16.provider.ProviderJelo;

/**
 * Created by androiddevelopment on 20.10.17..
 */

public class DetailFragment extends Fragment {

    private static int NOTIFICATION_ID = 1;

    private int position = 0;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            position = savedInstanceState.getInt("position", 0);
        }

        ImageView ivImage = (ImageView) getView().findViewById(R.id.iv_image);
        InputStream is = null;
        try {
            is = getActivity().getAssets().open(ProviderJelo.getJeloById(position).getImage());
            Drawable drawable = Drawable.createFromStream(is, null);
            ivImage.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Finds "tvName" TextView and sets "text" property
        TextView tvNaziv = (TextView) getView().findViewById(R.id.tv_naziv);
        tvNaziv.setText(ProviderJelo.getJeloById(position).getNaziv());

        // Finds "tvDescription" TextView and sets "text" property
        TextView tvOpis = (TextView) getView().findViewById(R.id.tv_opis);
        tvOpis.setText(ProviderJelo.getJeloById(position).getOpis());

        // Finds "tvName" TextView and sets "text" property
        TextView tvSastojci = (TextView) getView().findViewById(R.id.tv_sastojci);
        tvSastojci.setText(ProviderJelo.getJeloById(position).getSastojci());

        // Finds "tvDescription" TextView and sets "text" property
        TextView tvKalorijskaVrednost = (TextView) getView().findViewById(R.id.tv_kalorijskaVrednost);
        tvKalorijskaVrednost.setText(ProviderJelo.getJeloById(position).getKalorijskaVrednost() + "");

        TextView tvCena = (TextView) getView().findViewById(R.id.tv_cena);
        tvCena.setText(ProviderJelo.getJeloById(position).getCena() + "");

        // Finds "spCategory" Spiner and sets "selection" property
        Spinner category = (Spinner) getView().findViewById(R.id.sp_category);
        List<String> categories = ProviderCategory.getCategoryNaziv();
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, categories);
        category.setAdapter(adapter);
        category.setSelection((int)ProviderJelo.getJeloById(position).getCategory().getId());

        // Finds "btnBuy" Button and sets "onClickListener" listener
        // Finds "btnBuy" Button and sets "onClickListener" listener
        FloatingActionButton btnBuy = (FloatingActionButton) getView().findViewById(R.id.btn_buy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt("position", position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        return view;
    }

    public void setContent(final int position) {

        this.position = position;

        Log.v("DetailFragment", "setContent() sets position to " + position);
    }
    public void updateContent(final int position) {

        this.position = position;

        Log.v("DetailFragment", "updateContent() sets position to " + position);

        ImageView ivImage = (ImageView) getView().findViewById(R.id.iv_image);
        InputStream is = null;
        try {
            is = getActivity().getAssets().open(ProviderJelo.getJeloById(position).getImage());
            Drawable drawable = Drawable.createFromStream(is, null);
            ivImage.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Finds "tvName" TextView and sets "text" property
        TextView tvNaziv = (TextView) getView().findViewById(R.id.tv_naziv);
        tvNaziv.setText(ProviderJelo.getJeloById(position).getNaziv());

        // Finds "tvDescription" TextView and sets "text" property
        TextView tvOpis = (TextView) getView().findViewById(R.id.tv_opis);
        tvOpis.setText(ProviderJelo.getJeloById(position).getOpis());

        // Finds "tvName" TextView and sets "text" property
        TextView tvSastojci = (TextView) getView().findViewById(R.id.tv_sastojci);
        tvSastojci.setText(ProviderJelo.getJeloById(position).getSastojci());

        // Finds "tvDescription" TextView and sets "text" property
        TextView tvKalorijskaVrednost = (TextView) getView().findViewById(R.id.tv_kalorijskaVrednost);
        tvKalorijskaVrednost.setText(ProviderJelo.getJeloById(position).getKalorijskaVrednost() + "");

        TextView tvCena = (TextView) getView().findViewById(R.id.tv_cena);
        tvCena.setText(ProviderJelo.getJeloById(position).getCena() + "");

        // Finds "spCategory" Spiner and sets "selection" property
        Spinner category = (Spinner) getView().findViewById(R.id.sp_category);
        List<String> categories = ProviderCategory.getCategoryNaziv();
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, categories);
        category.setAdapter(adapter);
        category.setSelection((int)ProviderJelo.getJeloById(position).getCategory().getId());

        // Finds "btnBuy" Button and sets "onClickListener" listener
        // Finds "btnBuy" Button and sets "onClickListener" listener
        FloatingActionButton btnBuy = (FloatingActionButton) getView().findViewById(R.id.btn_buy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
            }
        });

    }
    private void showNotification() {
        // Creates notification with the notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity());
        Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_stat_buy);
        builder.setSmallIcon(R.drawable.ic_stat_buy);
        builder.setContentTitle(getActivity().getString(R.string.notification_title));
        builder.setContentText(getActivity().getString(R.string.notification_text));
        builder.setLargeIcon(bitmap);

        // Shows notification with the notification manager (notification ID is used to update the notification later on)
        NotificationManager manager = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());
    }
}
