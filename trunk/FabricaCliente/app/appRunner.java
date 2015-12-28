package app;

import javax.naming.InitialContext;

import view.aplicationWindow.ApplicationWindow;
import view.aplicationWindow.Splash;




public class appRunner {

		private static InitialContext initialContect;

		public static void main(String[] args) {
			Splash splash = new Splash("c:/imagenes/splash.jpg", 4000);
			ApplicationWindow win = new ApplicationWindow();
			win.open();
		} 
}
