# TextFileHandler
The command line application.

This application is designed on Java language.

Goals:

1. The app reads txt file and splits it on lines.
2. For each line makes statistics: longest word, smallest word, text lenght, 
average word length (the word is a set of characters between two spaces). The statistics aggregates for whole txt file as well.
3. The app saves the statistic to the DB MySQL with using JDBC.

Technical information:

1. The app is able to process all txt files in directory and subdirectories with using multithreading technology.
2. The app is processing each line with using multithreading technology.
3. The properties of db connection are located in the mysql_jdbc.properties file.

Default:

user=root

password=root

db.url=jdbc:mysql://localhost:3306/text_files

Dependencies:

1. MySQL DB "text_files" with created two tables "file_statement" and "file_statement_breakdown". 
There is the script is called "MySQL_script" in the repository that be able to create them.


