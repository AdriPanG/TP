package tp.pr5;

import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

import tp.pr5.control.ControladorConsole;
import tp.pr5.control.ControladorGui;
import tp.pr5.factoria.FactoriaComplica;
import tp.pr5.factoria.FactoriaConecta4;
import tp.pr5.factoria.FactoriaGravity;
import tp.pr5.factoria.FactoriaReversi;
import tp.pr5.factoria.FactoriaTipoJuego;
import tp.pr5.gui.VistaConsola;
import tp.pr5.gui.VistaVentana;
import tp.pr5.logica.Partida;
import tp.pr5.logica.TipoJuego;
import tp.pr5.movimiento.MovimientoInvalido;

/**
 * Clase que contiene el punto de entrada a la aplicación.
 * 
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class Main {

	/**
	 * Método principal de la aplicación.
	 * 
	 * @param args
	 *            - Argumentos pasados a la aplicación. No se utilizan.
	 * @throws MovimientoInvalido
	 */
	public static void main(String[] args) {

		@SuppressWarnings("unused")
		VistaVentana window;
		TipoJuego tipoJuego = null;
		FactoriaTipoJuego factoria = null;
		VistaConsola vista = null;
		ControladorGui controladorGui = null;
		String tamX = null;
		String tamY = null;
		String modo = null;
		int columna = 0, fila = 0;
		CommandLineParser parser = new PosixParser();
		/* Fase de inclusión de las operaciones que se permiten en el programa */
		Options options = new Options();
		@SuppressWarnings("static-access")
		Option g = OptionBuilder
				.withArgName("game")
				.hasArgs()
				.withLongOpt("game")
				.withDescription("Tipo de juego (c4, co, gr, rv). Por defecto, c4.")
				.create("g");
		options.addOption(g);
		@SuppressWarnings("static-access")
		Option u = OptionBuilder
				.withArgName("ui")
				.hasArgs()
				.withLongOpt("ui")
				.withDescription(
						"Tipo de interfaz (console, window). Por defecto, console.")
				.create("u");
		options.addOption(u);
		@SuppressWarnings("static-access")
		Option x = OptionBuilder
				.withArgName("columnNumber")
				.hasArgs()
				.withLongOpt("tamX")
				.withDescription(
						"Número de columnas del tablero (sólo para Gravity). Por defecto, 10.")
				.create("x");
		options.addOption(x);
		@SuppressWarnings("static-access")
		Option y = OptionBuilder
				.withArgName("rowNumber")
				.hasArgs()
				.withLongOpt("tamY")
				.withDescription(
						"Número de filas del tablero (sólo para Gravity). Por defecto, 10.")
				.create("y");
		options.addOption(y);
		options.addOption("h", "help", false, "Muestra esta ayuda.");

		/*
		 * Fase de parseo de los argumentos. En esta fase también se lanza la
		 * aplicación con el juego concreto.
		 */
		// Declaramos el parser

		try {

			CommandLine cmd = parser.parse(options, args);

			// Parseamos las operaciones
			if (cmd.hasOption('h')) {
				new HelpFormatter()
						.printHelp(
								"tp.pr5.Main [-g <game>] [-h] [-u <tipo>] [-x <columnNumber>] [-y <rowNumber>]",
								options);
			} else {
				if (cmd.hasOption('x')) {
					tamX = cmd.getOptionValue('x');
					columna = Integer.parseInt(tamX);
				}
				if (cmd.hasOption('y')) {
					tamY = cmd.getOptionValue('y');
					fila = Integer.parseInt(tamY);
				}
				if (cmd.hasOption('g')) {
					String arg = "";
					arg = cmd.getOptionValue('g');
					if (arg.equalsIgnoreCase("c4")) {
						tipoJuego = TipoJuego.CONECTA4;
						factoria = new FactoriaConecta4();
					} else if (arg.equalsIgnoreCase("co")) {
						tipoJuego = TipoJuego.COMPLICA;
						factoria = new FactoriaComplica();
					} else if (arg.equalsIgnoreCase("gr")) {
						tipoJuego = TipoJuego.GRAVITY;
						if (columna > 0 && fila > 0) {
							factoria = new FactoriaGravity(columna, fila);
						} else {
							factoria = new FactoriaGravity();
						}
					} else if(arg.equalsIgnoreCase("rv")) {
						factoria = new FactoriaReversi();
					} else {
						throw new MovimientoInvalido("Uso incorrecto: Juego '"
								+ cmd.getOptionValue('g') + "' incorrecto."
								+ "\nUse -h|--help para más detalles.");
					}
					if (cmd.getArgList().size() > 1) {
						throw new MovimientoInvalido(
								"Uso incorrecto: Argumentos no entendidos: "
										+ recogeArgs(cmd.getArgs())
										+ ".\nUse -h|--help para más detalles.");
					}
				} else {
					factoria = new FactoriaConecta4();
					tipoJuego = TipoJuego.CONECTA4;
					
				}

				Partida partida = new Partida(factoria.creaReglas());
				Scanner scanner = new Scanner(System.in);
				
				if (cmd.hasOption('u')) {
					modo = cmd.getOptionValue('u');
					try {
						if (modo.equalsIgnoreCase("window")) {
							controladorGui = new ControladorGui(factoria, partida);
							window = new VistaVentana(controladorGui, partida.getTablero().getAncho(), partida.getTablero().getAlto());
						} else if (modo.equalsIgnoreCase("console")){
							ControladorConsole controlador = new ControladorConsole(partida, factoria, tipoJuego);
							vista = new VistaConsola(controlador, scanner);
							vista.run();
						} 

					} catch (MovimientoInvalido e) {
						throw e;
					}
				} else {
					ControladorConsole controlador = new ControladorConsole(partida, factoria, tipoJuego);
					vista = new VistaConsola(controlador, scanner);
					vista.run();
				}

				scanner.close();
			}

			/*
			 * Tratamos todas las excepciones necesarias y, en el caso de que
			 * halla algún error, creamos un archivo de error en el que
			 * mostramos el error concreto que lanza el sistema para tener
			 * constancia de él
			 */
		} catch (org.apache.commons.cli.ParseException ex) {
			System.err.println("Uso incorrecto: " + ex.getMessage());
			System.err.println("Use -h|--help para más detalles.");
			System.exit(1);
		} catch (MovimientoInvalido e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}

	}

	/**
	 * Método que recoge todos los argumentos en una cadena
	 * 
	 * @param args
	 *            - Argumentos
	 * @return La cadena con los argumentos
	 */
	public static String recogeArgs(String[] args) {
		StringBuilder cadena = new StringBuilder();

		for (int i = 0; i < args.length; i++) {
			cadena.append(args[i]);
		}
		return cadena.toString();
	}
}
