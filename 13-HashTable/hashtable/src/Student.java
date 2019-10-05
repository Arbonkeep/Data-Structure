public class Student {
    private int grade;
    private int cls;
    private String firstName;
    private String lastName;

    public Student(int grade, int cls, String firstName, String lastName) {
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        //通过之前讲述的方法重写hashcode
        int B = 32;//我们随意指定B

        int hash = 0;

        hash = hash * B + grade;
        hash = hash * B + cls;
        hash = hash * B + firstName.hashCode();//由于我们需要返回一个整型值，所以引用Object中的hashCode()
        hash = hash * B + lastName.hashCode();

        return hash;
    }

    //重写equals方法
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if(this == o) {
            return true;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        Student s = (Student)o;//对o进行强制转换
        return this.grade == s.grade && this.cls == s.cls
                && this.firstName.toLowerCase().equals(s.firstName.toLowerCase())
                && this.lastName.toLowerCase().equals(s.lastName.toLowerCase());
    }

    //重写toString

    @Override
    public String toString() {
        return "Student{" +
                "grade=" + grade +
                ", cls=" + cls +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
