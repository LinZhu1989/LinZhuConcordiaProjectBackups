package Server_Replica;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * File name : RecordGenerator.java
 * 
 * COMP 6231 DISTRIBUTED SYSTEM DESIGN Project 1
 * 
 * @author Lin Zhu 6555659
 * 
 *         This is the RecordGenerator class which helps to generate both 2
 *         types of records.
 */

public class RecordGenerator {

	public RecordGenerator() {
	}

	/* randomFirstName() : to create a random first name with random character*/
	public static String randomFirstName() {
		final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final Random rand = new Random();
		final Set<String> Name = new HashSet<String>();
		StringBuilder builder = new StringBuilder();
		while (builder.toString().length() == 0) {
			int length = rand.nextInt(3) + 3;
			builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
			for (int i = 0; i < length; i++)
				builder.append(Character.toLowerCase(lexicon.charAt(rand
						.nextInt(lexicon.length()))));
			if (Name.contains(builder.toString()))
				builder = new StringBuilder();
		}
		return builder.toString();
	}

	/* randomLastName() : to create a random last name with random character*/
	public static String randomLastName(char c) {
		final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final Random rand = new Random();
		final Set<String> Name = new HashSet<String>();
		StringBuilder builder = new StringBuilder();
		while (builder.toString().length() == 0) {
			int length = rand.nextInt(3) + 3;
			builder.append(Character.toUpperCase(c));
			for (int i = 0; i < length; i++)
				builder.append(Character.toLowerCase(lexicon.charAt(rand
						.nextInt(lexicon.length()))));
			if (Name.contains(builder.toString()))
				builder = new StringBuilder();
		}
		return builder.toString();
	}

	/* randomCRID() : to create a random CR ID with random numbers*/
	public static String randomCRID() {
		final String lexicon = "0123456789";
		final String head[] = { "CR" };
		final Random rand = new Random();
		final Set<String> RecordID = new HashSet<String>();
		StringBuilder builder = new StringBuilder();
		while (builder.toString().length() == 0) {
			builder.append(head[0]);
			for (int i = 0; i < 5; i++)
				builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
			if (RecordID.contains(builder.toString()))
				builder = new StringBuilder();
		}
		return builder.toString();
	}

	/* randomMRID() : to create a random MR ID with random numbers*/
	public static String randomMRID() {
		final String lexicon = "0123456789";
		final String head[] = { "MR" };
		final Random rand = new Random();
		final Set<String> RecordID = new HashSet<String>();
		StringBuilder builder = new StringBuilder();
		while (builder.toString().length() == 0) {
			builder.append(head[0]);
			for (int i = 0; i < 5; i++)
				builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
			if (RecordID.contains(builder.toString()))
				builder = new StringBuilder();
		}
		return builder.toString();
	}
	    
	
	public static String randomAddress() {
		final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final String head[] = { " Road", " Street" };
		final String Direction[] = { " East", " South", " West", " North" };
		final String Province[] = { ",QC", ",AB", ",BC", ",ON", ",NS", ",NB",
				",PE", ",SK" };
		final Random rand = new Random();
		final Set<String> RecordID = new HashSet<String>();
		StringBuilder builder = new StringBuilder();
		while (builder.toString().length() == 0) {
			for (int i = 0; i < 5; i++)
				builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
			if (RecordID.contains(builder.toString()))
				builder = new StringBuilder();
		}
		builder.append(head[rand.nextInt(2)]);
		builder.append(Direction[rand.nextInt(4)]);
		builder.append(Province[rand.nextInt(8)]);
		return builder.toString();
	}

	public static String randomCRStatus() {

		final String CRStatus[] = { "captured", "on the run" };
		final Random rand = new Random();
		StringBuilder builder = new StringBuilder();
		builder.append(CRStatus[rand.nextInt(2)]);
		return builder.toString();
	}

	public static String randomMRStatus() {

		final String MRStatus[] = { "found", "missing" };
		final Random rand = new Random();
		StringBuilder builder = new StringBuilder();
		builder.append(MRStatus[rand.nextInt(2)]);
		return builder.toString();
	}

	public static String randomLastDate() {
		return String.format("%d/%d/%d", (int) (10 + (20) * Math.random()),
				(int) (10 + (2) * Math.random()),
				(int) (2000 + (13) * Math.random()));
	}

	public static String randomDescription() {

		final String MRStatus[] = { "tall", "fat", "young", "long hair",
				"in a suit", "strong", "smart" };
		final Random rand = new Random();
		StringBuilder builder = new StringBuilder();
		builder.append(MRStatus[rand.nextInt(7)]);
		return builder.toString();

	}

	/*
	 * There are two types of Records: CriminalRecord and MissingRecord. A
	 * Record can be identified by a unique RecordID starting with CR (for
	 * CriminalRecord) or MR (for MissingRecord) and ending with a 5 digits
	 * number (e.g. CR10000 for a CriminalRecord or MR10001 for a
	 * MissingRecord). A CriminalRecord also contains the first name and last
	 * name of the criminal, a description of the crime committed by this person
	 * and a status (captured/on the run). Similarly, a MissingRecord contains
	 * information about a missing individual. It holds the first name, last
	 * name, last known address, date and place last seen and a status
	 * (found/missing).
	 */

	public static String randomCRecord(char c) {

		return String.format("%s.%s,%s,%s", randomFirstName(),
				randomLastName(c), randomDescription(), randomCRStatus());
	}
	

	public static String randomMRecord(char c) {

		return String.format("%s.%s,%s,%s,%s,%s", randomFirstName(),
				randomLastName(c), randomAddress(), randomLastDate(),
				randomAddress(), randomMRStatus());
	}

}