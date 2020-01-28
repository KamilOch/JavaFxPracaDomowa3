package model;

public class Employee {

    private String firstName;
    private String lastName;
    private Integer roomNumber;
    private Integer workStartHour;
    private Integer workEndHour;

    public Employee(String firstName, String lastName, Integer roomNumber, Integer workStartHour, Integer workEndHour) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roomNumber = roomNumber;
        this.workStartHour = workStartHour;
        this.workEndHour = workEndHour;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getWorkStartHour() {
        return workStartHour;
    }

    public void setWorkStartHour(Integer workStartHour) {
        this.workStartHour = workStartHour;
    }

    public Integer getWorkEndHour() {
        return workEndHour;
    }

    public void setWorkEndHour(Integer workEndHour) {
        this.workEndHour = workEndHour;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + roomNumber + " " + workStartHour + " " + workEndHour;
    }
}
