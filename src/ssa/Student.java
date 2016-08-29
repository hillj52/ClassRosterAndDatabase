package ssa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Student implements Comparable<Student> {

	private static final int ID_SPACES = 9;
	private static final int NAME_SPACES = 15;
	private static final int EYE_COLOR_SPACES = 11;
	private static final int MONTHS_SPACES = 15;
	private static final String ID_TITLE = "Empl Id";
	private static final String FNAME_TITLE = "First Name";
	private static final String LNAME_TITLE = "Last Name";
	private static final String EYE_TITLE = "Eye Color";
	private static final String MONTHS_TITLE = "Months at SSA";
	private static final String MY_ID = "005255";
	
	private ClassRoster students;
	private String id;
	private String firstName;
	private String lastName;
	private String eyeColor;
	private int monthsEmployed;
	
	public void printClassRoster() {
		System.out.println("Student Class Roster\n");
		this.printHeader();
		students.printInOrder();
		System.out.println("\nOwn and Nearby Student Records");
		this.printHeader();
		System.out.println(students.getById(MY_ID));
		this.printNearby2();
	}
	
	public String toString() {
		String sb = this.getId() + this.padSpaces(ID_SPACES-this.getId().length());
		sb += this.getFirstName() + this.padSpaces(NAME_SPACES-this.getFirstName().length());
		sb += this.getLastName() + this.padSpaces(NAME_SPACES-this.getLastName().length());
		sb += this.getEyeColor() + this.padSpaces(EYE_COLOR_SPACES-this.getEyeColor().length());
		sb += this.getMonthsEmployed() + "";
		return sb;
	}
	
	public int compareTo(Student otherStudent) {
		return this.getFirstName().compareTo(otherStudent.getFirstName());
	}
	
	private void printNearby2() {
		Set<String> idSet = students.getKeySet();
		Iterator<String> idIterator = idSet.iterator();
		ArrayList<String> keys = new ArrayList<String>();
		while (idIterator.hasNext()) {
			keys.add(idIterator.next());
		}
		keys.sort(null);
		int myLocation = keys.indexOf(MY_ID);
		if (myLocation == 0) {
			System.out.println(students.getById(keys.get(myLocation+1)));
			System.out.println(students.getById(keys.get(myLocation+2)));
		} else if (myLocation == (keys.size()-1)) {
			System.out.println(students.getById(keys.get(myLocation-1)));
			System.out.println(students.getById(keys.get(myLocation-2)));
		} else {
			System.out.println(students.getById(keys.get(myLocation-1)));
			System.out.println(students.getById(keys.get(myLocation+1)));
		}
	}
	
	private String padSpaces(int numSpaces) {
		String spaces = "";
		for (int i=0;i<numSpaces;i++) {
			spaces += " ";
		}
		return spaces;
	}
	
	private void printHeader() {
		System.out.println(ID_TITLE + this.padSpaces(ID_SPACES - ID_TITLE.length()) +
				FNAME_TITLE + this.padSpaces(NAME_SPACES - FNAME_TITLE.length()) +
				LNAME_TITLE + this.padSpaces(NAME_SPACES - LNAME_TITLE.length()) +
				EYE_TITLE + this.padSpaces(EYE_COLOR_SPACES - EYE_TITLE.length()) +
				MONTHS_TITLE);
		for (int i=0;i<ID_SPACES-2;i++) {
			System.out.printf("=");
		}
		System.out.printf("  ");
		for (int i=0;i<NAME_SPACES-2;i++) {
			System.out.printf("=");
		}
		System.out.printf("  ");
		for (int i=0;i<NAME_SPACES-2;i++) {
			System.out.printf("=");
		}
		System.out.printf("  ");
		for (int i=0;i<EYE_COLOR_SPACES-2;i++) {
			System.out.printf("=");
		}
		System.out.printf("  ");
		for (int i=0;i<MONTHS_SPACES-2;i++) {
			System.out.printf("=");
		}
		System.out.println();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEyeColor() {
		return eyeColor;
	}
	public void setEyeColor(String eyeColor) {
		if (eyeColor == "Blue" || eyeColor == "Brown")
			this.eyeColor = eyeColor;
		else
			this.eyeColor = "Other";
	}
	public int getMonthsEmployed() {
		return monthsEmployed;
	}
	public void setMonthsEmployed(int monthsEmployed) {
		if (monthsEmployed >= 0)
			this.monthsEmployed = monthsEmployed;
	}
	public Student(String id, String firstName, String lastName, String eyeColor, int monthsEmployed) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEyeColor(eyeColor);
		this.setMonthsEmployed(monthsEmployed);
	}
	
	public Student() {
		students = ClassRoster.getInstance();
	}

}

class ClassRoster {

	private static ClassRoster instance;
	
	private ArrayList<Student> studentList;
	private HashMap<String,Student> studentMap;
	
	public Student getById(String id) {
		return studentMap.get(id);
	}
	
	public Set<String> getKeySet() {
		return this.studentMap.keySet();
	}
	
	public static ClassRoster getInstance() {
		if (instance == null) {
			instance = new ClassRoster();
		}
		return instance;
	}
	
	public void printInOrder() {
		this.sort();
		for (Student s:studentList) {
			System.out.println(s);
		}
	}
	
	private void sort() {
		studentList.sort(null);
	}
	
	private void add(Student s) {
		this.studentList.add(s);
		this.studentMap.put(s.getId(), s);
	}
	
	private ClassRoster() {
		studentList = new ArrayList<Student>();
		studentMap = new HashMap<String,Student>();
		this.add(new Student("001122","Michael","Clair","Purple",12));
		this.add(new Student("001212","Stephen","Rook","Brown",11));
		this.add(new Student("474143","Jonathan","Stafford","Brown",13));
		this.add(new Student("005295","Kyle","Deen","Blue",2));
		this.add(new Student("004400","Kevin","Tran","Red",12));
		this.add(new Student("132617","Rueben","Turner","Blue",12));
		this.add(new Student("306700","Li","Lui","Brown",12));
		this.add(new Student("215296","Joshua","Franey","Other",27));
		this.add(new Student("523420","Gabriel","Hamilton","Black",10));
		this.add(new Student("004014","Aisha","Covington","Brown",10));
		this.add(new Student("006789","Arun","Soma","Brown",2));
		this.add(new Student("009999","Steve","Ellwood","Green",2));
		this.add(new Student("343769","Shaquil","Timmons","Brown",11));
		this.add(new Student("001449","Karen","Reiter","Blue",10));
		this.add(new Student("267252","Dax","Richards","Brown",12));
		this.add(new Student("229949","Mike","Sykes","Brown",13));
		this.add(new Student("772223","Daniel","Kiros","Brown",3));
		this.add(new Student("004444","Peter","Choi","Brown",2));
		this.add(new Student("005255","Joe","Hill","Brown",13));
		this.add(new Student("008888","Evan","Tizard","Brown",12));
	}
}

