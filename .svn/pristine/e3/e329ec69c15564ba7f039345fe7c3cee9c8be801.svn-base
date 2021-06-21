package com.setes.setesvendas.app.pedidovenda;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.setes.setesvendas.app.controller.EtdCompany;
import com.setes.setesvendas.app.controller.EtdCustomer;
import com.setes.setesvendas.app.controller.EtdEntity;
import com.setes.setesvendas.app.controller.EtdMailing;
import com.setes.setesvendas.app.controller.EtdOrder;
import com.setes.setesvendas.app.controller.EtdOrderSale;
import com.setes.setesvendas.app.controller.EtdPerson;
import com.setes.setesvendas.app.model.CompanyHandler;
import com.setes.setesvendas.app.model.CustomerHandler;
import com.setes.setesvendas.app.model.EntityHandler;
import com.setes.setesvendas.app.model.MailingHandler;
import com.setes.setesvendas.app.model.OrderHandler;
import com.setes.setesvendas.app.model.OrderSaleHandler;
import com.setes.setesvendas.app.model.PersonHandler;
import com.setes.setesvendas.app.util.Mask;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.setes.setesvendas.app.R;

public class CustomerActivity extends Activity
{

    public EditText Edt_cpfcnpj;
    public EditText Edt_email;
    public EditText Edt_name_company;
    public EditText Edt_nick_trade;
    public EditText Edt_rg_ie;
    public TextView Lbl_Cpf_cnpj;
    public TextView Lbl_name_company;
    public TextView Lbl_nick_trade;
    public TextView Lbl_rg_ie;
    public RadioButton Rdb_company;
    public RadioButton Rdb_person;
    public RadioGroup RdgKindCustomer;
    private TextWatcher cpfMask;
    private TextWatcher cnpjMask;

    public CustomerActivity()
    {
    }

    public void cancelCustomer(View view)
    {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
        finish();
    }

    public void definePersonCompany()
    {
        Edt_cpfcnpj.removeTextChangedListener(cnpjMask);
        Edt_cpfcnpj.removeTextChangedListener(cpfMask);
        if (Rdb_person.isChecked())
        {
            Lbl_name_company.setText("Nome do Cliente");
            Lbl_nick_trade.setText("Apelido/Codinome");
            Lbl_Cpf_cnpj.setText("C.P.F.");
            Edt_cpfcnpj.addTextChangedListener(cpfMask);
            Lbl_rg_ie.setText("R.G/Identificação");
        } else
        {
            Lbl_name_company.setText("Razão Social");
            Lbl_nick_trade.setText("Nome Fantasia");
            Lbl_Cpf_cnpj.setText("C.N.P.J");
            Edt_cpfcnpj.addTextChangedListener(cnpjMask);
            Lbl_rg_ie.setText("Inscrição Estadual");
        }
    }

    protected void listener()
    {
        RdgKindCustomer.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup radiogroup, int i) {
                definePersonCompany();
            }

        });
    }

    protected void loadData()
    {
        Rdb_person.setEnabled(true);
        Rdb_company.setEnabled(true);
        if (MainActivity.gbCustomer > 0) {
            EtdEntity objEntity = new EtdEntity();
            EntityHandler hdlEntity = new EntityHandler(this);
            objEntity = hdlEntity.get(MainActivity.gbCustomer);
            if (objEntity != null) {
                Edt_name_company.setText(objEntity.getName_company());
                Edt_nick_trade.setText(objEntity.getNick_trade());

                EtdCompany objCompany = new EtdCompany();
                CompanyHandler hdlCompany = new CompanyHandler(this);
                objCompany = hdlCompany.get(MainActivity.gbCustomer);
                if (objCompany != null) {
                    //Rdb_person.setEnabled(false);
                    Rdb_person.setChecked(false);
                    //Rdb_company.setEnabled(true);
                    Rdb_company.setChecked(true);
                    definePersonCompany();
                    Edt_cpfcnpj.setText(objCompany.getCnpj());
                    Edt_rg_ie.setText(objCompany.getIe());
                } else {
                    EtdPerson objPerson = new EtdPerson();
                    PersonHandler hdlPerson = new PersonHandler(this);
                    objPerson = hdlPerson.get(MainActivity.gbCustomer);
                    if (objPerson != null) {
                        Rdb_company.setChecked(false);
                        //Rdb_company.setEnabled(false);
                        //Rdb_person.setEnabled(true);
                        Rdb_person.setChecked(true);
                        definePersonCompany();
                        Edt_cpfcnpj.setText(objPerson.getCpf());
                        Edt_rg_ie.setText(objPerson.getRg());
                    }
                    hdlPerson.close();
                }
                EtdMailing objMailing = new EtdMailing();
                MailingHandler hdlMailing = new MailingHandler(this);
                objMailing = hdlMailing.get(MainActivity.gbCustomer, "Comercial");
                if (objMailing != null) {
                    Edt_email.setText(objMailing.getEmail());
                }
                hdlCompany.close();
                hdlMailing.close();
            }
            hdlEntity.close();
        }

    }

    protected void loadElement()
    {
        RdgKindCustomer = (RadioGroup)findViewById(R.id.rdg_kindPerson );
        Rdb_person = (RadioButton)findViewById(R.id.rdb_person);
        Rdb_company = (RadioButton)findViewById(R.id.rdb_company);
        Lbl_name_company = (TextView)findViewById(R.id.lblNameCompanyCustomer);
        Edt_name_company = (EditText)findViewById(R.id.edtNameCompanyCustomer );
        Lbl_nick_trade = (TextView)findViewById(R.id.lblNickTradeCustomer);
        Edt_nick_trade = (EditText)findViewById(R.id.edtNickTradeCustomer);
        Lbl_Cpf_cnpj = (TextView)findViewById(R.id.lblCpfCNPJCustomer );
        Edt_cpfcnpj = (EditText)findViewById(R.id.edtCpfCNPJCustomer);
        Lbl_rg_ie = (TextView)findViewById(R.id.lblRgIeCustomer);
        Edt_rg_ie = (EditText)findViewById(R.id.edtRgIeCustomer);
        Edt_email = (EditText)findViewById(R.id.edtEmailCustomer);

        cpfMask = Mask.insert("###.###.###-##", Edt_cpfcnpj);
        cnpjMask = Mask.insert("##.###.###/####-##", Edt_cpfcnpj);
        Edt_cpfcnpj.addTextChangedListener(cnpjMask);
    }

    public void newCustomerActivity(View view)
    {

        new AlertDialog.Builder(CustomerActivity.this)
                .setTitle("Novo Cliente")
                .setMessage("Tem certeza cadastrar um novo cliente?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                               //novo cliente
                            }
                        }).setNegativeButton(android.R.string.no, null)
                .show();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_customer);
        getWindow().setSoftInputMode(3);
        loadElement();
        loadData();
        listener();
    }

    public void saveCompany(View view)
    {
        CompanyHandler hdl = new CompanyHandler(this);
        EtdCompany obj = new EtdCompany();
        String cnpj = "";
        cnpj =  Mask.unmask(Edt_cpfcnpj.getText().toString());
        obj.setId(MainActivity.gbCustomer);
        obj.setCnpj(cnpj);
        obj.setIe(Edt_rg_ie.getText().toString());
        if (hdl.verifyRegisterByField("tb_company", "cnpj", cnpj))
        {
            hdl.update(obj);
        } else
        {
            hdl.add(obj);
        }
    }

    public void saveCustomer()
    {
        CustomerHandler hdl = new CustomerHandler(this);
        EtdCustomer obj = new EtdCustomer();
        obj.setId(MainActivity.gbCustomer);
        if (hdl.verifyRegisterByField("tb_customer", "id", Integer.toString(MainActivity.gbCustomer)))
        {
            hdl.update(obj);
        } else
        {
            hdl.add(obj);
        }
    }

    public void saveCustomerActivity(View view)
    {
        if (validaAddCustomer())
        {
            if (MainActivity.gbCustomer == 0)
            {
                if (Rdb_person.isChecked())
                {
                    PersonHandler hdlPerson = new PersonHandler(this);
                    MainActivity.gbCustomer = hdlPerson.getIdByCpf(Mask.unmask( Edt_cpfcnpj.getText().toString()));
                    hdlPerson.close();
                } else
                {
                    CompanyHandler hdlcompany = new CompanyHandler(this);
                    MainActivity.gbCustomer = hdlcompany.getIdByCNPJ(Mask.unmask( Edt_cpfcnpj.getText().toString()));
                    hdlcompany.close();
                }
            }
            saveEntity(view);
            if (Rdb_person.isChecked())
            {
                savePerson(view);
            } else
            {
                saveCompany(view);
            }
            saveCustomer();
            saveMailing();
            updateCustomerOrder();
            Toast.makeText(this, "Informação Atualizada", Toast.LENGTH_SHORT ).show();
            cancelCustomer(view);
        }
    }

    public void saveEntity(View view)
    {
        EtdEntity obj = new EtdEntity();
        EntityHandler hdl = new EntityHandler(this);
        if (MainActivity.gbCustomer == 0)
        {
            MainActivity.gbCustomer = hdl.getNexId("tb_entity");
        }
        obj.setId(MainActivity.gbCustomer);
        obj.setName_company(Edt_name_company.getText().toString());
        obj.setNick_trade(Edt_nick_trade.getText().toString());
        if (hdl.verifyRegisterByField("tb_entity", "id", Integer.toString(MainActivity.gbCustomer)))
        {
            hdl.update(obj);
        } else
        {
            hdl.add(obj);
        }
    }

    public void saveMailing()
    {
        MailingHandler hdl = new MailingHandler(this);
        EtdMailing obj = new EtdMailing();
        obj.setId(MainActivity.gbCustomer);
        obj.setEmail(Edt_email.getText().toString());
        obj.setKind("Comercial");
        obj.setNews("N'");
        if (hdl.verifyRegisterByField("tb_mailing", "id", Integer.toString(MainActivity.gbCustomer)))
        {
            hdl.update(obj);
        } else
        {
            hdl.add(obj);
        }
    }

    public void savePerson(View view)
    {
        PersonHandler hdl = new PersonHandler(this);
        EtdPerson obj = new EtdPerson();
        obj.setId(MainActivity.gbCustomer);
        String cpf = "";
        cpf =  Mask.unmask(Edt_cpfcnpj.getText().toString());
        obj.setCpf(cpf);
        obj.setRg(Edt_rg_ie.getText().toString());
        if (hdl.verifyRegisterByField("tb_person", "id", Integer.toString(MainActivity.gbCustomer)))
        {
            hdl.update(obj);
        } else
        {
            hdl.add(obj);
        }
    }

    public void searchCustomer(View view)
    {
        Intent intent = new Intent(CustomerActivity.this, SearchCustomerActivity.class);
        startActivity(intent);
        finish();
    }

    public void updateCustomerOrder()
    {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        EtdOrder obj = new EtdOrder();
        obj.setId(MainActivity.gbNumber);
        obj.setTb_institution_id(MainActivity.gbInstitution);
        obj.setDt_record(formatter.format(date));
        obj.setNote("");
        obj.setStatus("N");

        OrderHandler hdl = new OrderHandler(this);
        if (hdl.verifyRegisterByField("tb_order", "id", Integer.toString(MainActivity.gbNumber)))
        {
            hdl.update(obj);
        }else{
            hdl.add(obj);
        }
        EtdOrderSale objOrderSale = new EtdOrderSale();
        objOrderSale.setId(MainActivity.gbNumber);
        objOrderSale.setTb_institution_id(MainActivity.gbInstitution);
        objOrderSale.setTb_customer_id(MainActivity.gbCustomer);
        objOrderSale.setTb_salesman_id(MainActivity.gbSalesman);
        objOrderSale.setNumber(MainActivity.gbNumber);
        OrderSaleHandler hdlOrderSale = new OrderSaleHandler(this);
        if (hdlOrderSale.verifyRegisterByField("tb_order_sale", "id", Integer.toString(MainActivity.gbNumber)))
        {
            hdlOrderSale.updateCustomer(objOrderSale);
        } else
        {
            hdlOrderSale.add(objOrderSale);
        }
    }

    public boolean validaAddCustomer()
    {
        if (Edt_name_company.getText().toString().equals("")) {
            Toast.makeText(this, "Nome/Razão do Cliente não informado", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Edt_nick_trade.getText().toString().equals("")){
            Toast.makeText(this, "Apelido/Fantasia do Cliente não informado", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Rdb_person.isChecked()) {
            if (Edt_cpfcnpj.getText().toString().equals("")) {
                Toast.makeText(this, "C.P.F. do Cliente não informado", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else {
            if (Edt_cpfcnpj.getText().toString().equals("")) {
                Toast.makeText(this, "C.N.P.J. do Cliente não informado", Toast.LENGTH_SHORT).show();
                return false;
            }

            if (Edt_rg_ie.getText().toString().equals("")) {
                Toast.makeText(this, "Incrição do Cliente não informado", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        return true;
    }
}
