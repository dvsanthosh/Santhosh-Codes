package practice.inheri;

public class Students extends Person{
	String course;
	public Students(){
		name = "Stud";
		course = " CE";
	}
	
	public void display(){
		//super.display();
		System.out.println(course + no + name);
		
	}
	
}