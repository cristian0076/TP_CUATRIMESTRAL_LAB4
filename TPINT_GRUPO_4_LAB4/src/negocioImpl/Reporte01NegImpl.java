package negocioImpl;

import java.util.ArrayList;
import java.util.List;


import datosImpl.Reporte01DaoImpl;
import entidad.Reporte01;
import negocio.Reporte01Neg;

public class Reporte01NegImpl implements Reporte01Neg {

	private Reporte01DaoImpl rdao = new Reporte01DaoImpl();
	
	@Override
	public List<Reporte01> listarinforme(int mes) {
		
		return (ArrayList<Reporte01>)rdao.listarinforme(mes);
	}

	@Override
	public List<Reporte01> listarinforme2(int mes) {
		return (ArrayList<Reporte01>)rdao.listarinforme2(mes);
	}

}
