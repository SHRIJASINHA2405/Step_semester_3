package oop.class_problems;
import java.util.*;
class PlacementRecord {
    String studentName;
    String company;
    double packageLpa;
    PlacementRecord(String studentName, String company, double packageLpa) {
        this.studentName = studentName;
        this.company = company;
        this.packageLpa = packageLpa;
    }
    void printRecord() {
        System.out.println(studentName + " -> " + company + " @ " + packageLpa + " LPA");
    }
}
public class PlacementRecordDemo {
     static void main(String [] ) {
        Scanner sc = new Scanner(System.in);
        PlacementRecord[] records = new PlacementRecord[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter student name: ");
            String name = sc.nextLine();
            System.out.print("Enter company name: ");
            String company = sc.nextLine();
            System.out.print("Enter package in LPA: ");
            double packageLpa = sc.nextDouble();
            sc.nextLine();
            records[i] = new PlacementRecord(name, company, packageLpa);
        }
        System.out.println("\nPlacement Records:");
        for (int i = 0; i < 3; i++) {
            records[i].printRecord();
        }
    }
}