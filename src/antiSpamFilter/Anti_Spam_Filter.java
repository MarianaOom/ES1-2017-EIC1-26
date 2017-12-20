package antiSpamFilter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import gui.Window;

/**
 * @author Group 26
 *
 */

public class Anti_Spam_Filter {

	private Window window;
	private ArrayList<Rules> rules = new ArrayList<Rules>();
	private int FP = 0;
	private int FN = 0;
	private ArrayList<Message> messages = new ArrayList<Message>();

	public static void main(String[] args) {
		new Anti_Spam_Filter();
	}

	/**
	 * This method initiates the application window for the antiSpamFilter.
	 * 
	 */
	public Anti_Spam_Filter() {
		window = new Window(this);
		window.setVisible(true);
	}

	public ArrayList<Rules> getRules() {
		return rules;
	}

	/**
	 * This method locates the rules file and reads it.
	 * warns user if it can't find the file.
	 * @param name the path for the rules file.
	 */
	public void prepareRules(String path) {
		File fileRules = new File(path);
		try {
			Scanner scannerRules = new Scanner(fileRules);
			System.out.println(" Rules - " + scannerRules.hasNextLine());
			while (scannerRules.hasNextLine())
				rules.add(new Rules(scannerRules.nextLine()));
		} catch (Exception e) {
			System.out.println("Failed to locate file rules!");
		}
	}
	
	/**
	 * This method locates the HAM file and reads it.
	 * warns user if it can't find the file.
	 * @param name the path for the HAM file.
	 */
	public void readHam(String path) {
		File fileHam = new File(path);
		try {
			Scanner scannerHam = new Scanner(fileHam);
			while (scannerHam.hasNextLine())
				createMessage(scannerHam.nextLine(), 1);
		} catch (Exception e) {
			System.out.println("Failed to locate HAM file!");

		}
	}

	/**
	 * This method locates the SPAM file and reads it.
	 * warns user if it can't find the file.
	 * @param name the path for the SPAM file.
	 */
	public void readSpam(String path) {
		File fileSpam = new File(path);
		try {
			Scanner scannerSpam = new Scanner(fileSpam);
			while (scannerSpam.hasNextLine()) {
				createMessage(scannerSpam.nextLine(), 0);
			}
		} catch (Exception e) {
		}

	}

	/**
	 * This method reads one of the SPAM or HAM files.
	 * In every line associates the message to it's set of rules and adds a message with it's set of rules, to the message list.
	 * @param String type that splits the line in the file.  
	 * @param Integer that defines if we are reading the HAM or SPAM file.
	 */
	public void createMessage(String s, int i) {
		String[] line = s.split("\\t");
		Message m;
		if (i == 1)
			m = new Ham(line[0]);
		else
			m = new Spam(line[0]);
		for (int k = 1; k < line.length; k++)
			for (Rules rule : rules) {
				if (rule.getName().contains(line[k].trim()))
					m.addRules(rule);
			}
		messages.add(m);
	}

	/**
	 * This method evaluates the messages in the message list, for
	 * the manual evaluation.
	 * For each message, reads it's rules and calculates the weight 
	 * of the message, if the message is from the SPAM file and the 
	 * weight is less than five it adds one to the false positives,
	 * if the message is from the HAM file and the weight is more 
	 * than five it adds one to the false negatives. 
	 * In the end prints the results of the false positives and false 
	 * negatives in the application window.
	 */
	public void evaluate() {
		FN = 0;
		double weight = 0.0;
		for (Message message : messages) {
			for (Rules rule : message.getRules())
				weight += rule.getWeight();
			if (weight > 5 && message instanceof Ham)
				FN++;
			if (weight < 5 && message instanceof Spam)
				FP++;
			weight = 0.0;
		}
		System.out.println("Falsos Positiovos - " + FP);
		System.out.println("Falsos Negativos  - " + FN);
		window.setManualResults(FP, FN);

	}

	/**
	 * This method initiates the AntiSpamFilterAutomaticConfiguration which set's the weights  of the rules
	 * and saves them on the NSGAII file.
	 * In the end gives the results of the false positives and false negatives to the application window.
	 * 
	 */
	public void automaticEvaluation() {
		try {
			new AntiSpamFilterAutomaticConfiguration(this);
			Scanner scann = new Scanner(
					new File("experimentBaseDirectory/referenceFronts/AntiSpamFilterProblem.NSGAII.rs"));
			String[] result = scann.nextLine().split(" ");
			for (int i = 0; i < result.length; i++)
				rules.get(i).setWeight(Double.parseDouble(result[i]));
			for (Rules ru : rules)
				System.out.println(ru.getWeight());
			window.setAutomaticResults(Integer.parseInt(result[result.length - 2].split(": ")[1]),
					Integer.parseInt(result[result.length - 1].split(": ")[1]));
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}

	/**
	 * This method saves the results of the evaluations.
	 * It is invoked in the class window when the button "save" is pressed 
	 * @param Integer type that defines if the save was made in the automatic 
	 * configuration or in the manual configuration.
	 */
	public void printResults(int type) {
		File[] r = (new File("Rules")).listFiles();
		String lastName = r[r.length - 1].getName();
		int number = Integer.parseInt(lastName.split("s")[1].split(".c")[0]);
		number++;
		try {
			File f;
			if (type == 0)
				f = new File("Rules/rules" + number + ".cf");
			else
				f = new File("Rules/Automatic_rules" + number + ".cf");
			FileWriter fi = new FileWriter("Rules/rules" + number + ".cf");
			BufferedWriter printer = new BufferedWriter(fi);
			for (Rules rule : rules) {
				String line = rule.getName() + "\t" + rule.getWeight();
				printer.write(line);
				printer.newLine();
			}
			printer.write("FP:\t" + FP);
			printer.newLine();
			printer.write("FN:\t" + FN);
			printer.close();
			fi.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method evaluates the messages in the message list for 
	 * the automatic evaluation.
	 * For each message, reads it's rules and calculates the weight 
	 * of the message, if the message is from the SPAM file and the 
	 * weight is less than five it adds one to the false positives,
	 * if the message is from the HAM file and the weight is more 
	 * than five it adds one to the false negatives. 
	 * @param A double vector that set's all the weights of the rules
	 * in the automatic evaluation. 
	 * @return The results of the false positives an false negatives in
	 * in a vector with both results.
	 */
	public int[] evaluateAutomatic(double[] x) {
		FP = 0; 
		FN = 0;
		for (int i = 0; i < x.length; i++)
			rules.get(i).setWeight(x[i]); 
		double weight = 0.0;
		for (Message message : messages) {
			for (Rules rule : message.getRules())
				weight += rule.getWeight();
			if (weight > 5 && message instanceof Ham)
				FN++;
			if (weight < 5 && message instanceof Spam)
				FP++;
			weight = 0.0;
		}
		int[] result = new int[2];
		result[0] = FP;
		result[1] = FN;
		return result;
	}

}
