package Ejericio1;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Biblioteca")
public class MisLibrerias {

    private ArrayList<Libreria> misLibrerias;

    public MisLibrerias() {
    	misLibrerias=new ArrayList<Libreria>();
    }

    public MisLibrerias(ArrayList<Libreria> librerias) {
        this.misLibrerias = librerias;
    }

    @XmlElement(name = "Libreria")
    public ArrayList<Libreria> getMisLibrerias() {
        return misLibrerias;
    }

    public void setMisLibrerias(ArrayList<Libreria> misLibrerias) {
        this.misLibrerias = misLibrerias;
    }
}
