/*
Name: Richard Dip
Student Number: N00653804

 */
//
//package comricharddip28.httpsgithub.richard;
//
//public class RichardQ33 {
//
//    Disclaimer: I forgot the specifics of the two questions so my answers are based off oh what I remember.
//
//    1. Write the java code to retrieve all student names that are taking 10 or more courses
//
//    String[] tableColumns = new String[]{
//            "SELECT 'first_name' + ' ' + 'last_name' as 'Name' FROM EnrolledStudentsTable"
//    };
//    String whereClause = "COUNT(Course) >= ?";
//    String whereArgs ="10";
//
//    Cursor c = sqLiteDataBase.query("EnrolledStudentsTable", tableColumns, whereClause, whereArgs, null, null, 0);
//
//
//
//
//    2. Update every student with the last name Smith to have 0 courses.
//
//     String[] tableColums = new String[]{
//            "UPDATE table_name",
//            "SET NumberOfCourses = ?"
//    };
//    String whereClause = "last_name = ?";
//    String[] whereArgs = new String[]{
//            "10",
//            "Smith"
//    };
//
//    Cursor c = sqLiteDatabase.query("table_name", tableColumns, whereClause, whereArgs, null, null, 0);
//
//
//
//
//}
