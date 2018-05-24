package com.wavemaker.arraylist;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (o == this) {
                return true;
            } else {
                if (o instanceof Employee) {
                    Employee employee = (Employee) o;
                    return id == employee.getId();
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result += 31 * result + ((firstName != null) ? firstName.hashCode() : 1);
        result += 31 * result + ((lastName != null) ? lastName.hashCode() : 1);
        result += id == 0 ? result : result * id;
        return result;
    }
}
