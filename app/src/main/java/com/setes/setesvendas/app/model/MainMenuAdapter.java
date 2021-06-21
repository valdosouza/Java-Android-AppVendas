package com.setes.setesvendas.app.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.setes.setesvendas.app.R;

public class MainMenuAdapter extends BaseAdapter
{

    private Context mContext;

    private Integer[] mThumbIds = {
            R.drawable.i_config,
            R.drawable.i_novaos,
            R.drawable.i_pesquisaos,
            R.drawable.upload,
            R.drawable.download,
            R.drawable.i_dell_all,
            R.drawable.i_pricetag,
            R.drawable.i_sair

    };
    private String[] mTitulos = {"Configurações","Novo Pedido","Pesquisar Pedido","Enviar Informações","Receber dados","Limpar Dados","Tabela de Preço","Sair"};


    public MainMenuAdapter(Context context)
    {
        mContext = context;
    }

    public int getCount()
    {

        return mThumbIds.length;
    }

    public Object getItem(int i)
    {

        return null;
    }

    public long getItemId(int i)
    {

        return 0L;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v;
        LayoutInflater li = ((Activity) mContext).getLayoutInflater();
        v = li.inflate(R.layout.icon, null);
        TextView tv = (TextView)v.findViewById(R.id.icon_text);
        tv.setText(mTitulos[position]);
        ImageView iv = new ImageView(mContext);
        iv = (ImageView)v.findViewById(R.id.icon_image);
        iv.setImageResource(mThumbIds[position]);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setPadding(8, 8, 8, 8);
        return v;
    }


}
