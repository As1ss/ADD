package Ejemplos;

import java.io.*;

public class EjemploListas {

	public static void main(String[] args) {

		File dir = new File(".");

		for (String list : dir.list()) {
			System.out.println(list);
		}

	}

}

