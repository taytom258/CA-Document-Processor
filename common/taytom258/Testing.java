package taytom258;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleScriptContext;
public class Testing { 
	
	
	public static void scriptManager() throws Exception { 
		// create a script engine manager 
		ScriptEngineManager factory = new ScriptEngineManager(); 
		// create a JavaScript engine 
		ScriptEngine engine = factory.getEngineByName("JavaScript"); 
		// evaluate JavaScript code from String 
		SimpleScriptContext context = new SimpleScriptContext();
		context.setAttribute("parseTSO", "Test", context.GLOBAL_SCOPE);
		engine.eval(new java.io.FileReader("TSO_Parser.js"), context);
		engine.eval("print ('hello world')");	
	}
	
	public static void webPage() throws IOException{
		// using this in real life, you'd probably want to check that the desktop
		// methods are supported using isDesktopSupported()...

		String htmlFilePath = "TSO_Parser.htm"; // path to your new file
		File htmlFile = new File(htmlFilePath);

		// open the default web browser for the HTML page
		Desktop.getDesktop().browse(htmlFile.toURI());

		// if a web browser is the default HTML handler, this might work too
		Desktop.getDesktop().open(htmlFile);
	}
	
	public static void regEx(){
		String text = "Hello\nThi\"s isn't a\r test";
		System.out.println(text);
		text = text.replaceAll("[\n|\r]", " ");
		text = text.replaceAll("\\s+", " ");
		text = text.replace("'", "");
		text = text.replace("\"", "");
		System.out.println(text);
	}
}