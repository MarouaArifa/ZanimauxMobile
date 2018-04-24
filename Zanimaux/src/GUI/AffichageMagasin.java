/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Magasin;
import Entities.Produit;
import Services.MagasinService;
import Services.ProduitService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author macbookpro
 */
public class AffichageMagasin 
{
    private Resources theme;
    Form f;
   
    
    
    public AffichageMagasin() { 
        theme = UIManager.initFirstTheme("/theme");
        f = new Form(new BoxLayout(BoxLayout.Y_AXIS));       
        MagasinService ms=new MagasinService();
        ArrayList<Magasin> lis=ms.getAllMagasin();
        for (int i =0;i<lis.size();i++)
            
        {   Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            SpanLabel lb = new SpanLabel("");
            Button b =new Button("Cosulter Magasin");
            //ImageViewer iv = new ImageViewer(theme.getImage("key.png").scaled(20, 20));
            ImageViewer iv = new ImageViewer(theme.getImage(lis.get(i).getPhotoMagasin()).scaled(350, 200));
            Label t =new Label(lis.get(i).getAdresseMagasin()+" "+lis.get(i).getVilleMagasin()+", "+lis.get(i).getCodePostaleMagasin());
            Magasin m = lis.get(i);
            b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ProduitService ps = new ProduitService();
                ArrayList<Produit> liste = ps.getAllProduit(m.getIdMagasin());
                AffichageProduit FormProduit = new AffichageProduit(liste);
                FormProduit.getF().show();
            }
        });
            c.add(iv);
            c.add(lb);
            c.add(t);
            c.add(b);
            f.add(c);    
            lb.setText(lis.get(i).getNomMagasin());
        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
