# Read and Write files with Java

As the main README file says, this repository will only explain the following read&write methods.

## Byte Stream

-  FileInputStream (**read**): `new FileInputStream("file_path");`

   This object allows us to read the file content. It reads every character as an unicode/ascii code (int) until it turns -1, that means that there is nothing else to read.

```java
try (FileInputStream fin = new FileInputStream("files/input.txt")) { // Create InputStream inside the Try-Catch block
   int i; // This variable will store the ascii/unicode character code of every character in the read file
   do { // Loop through all char
      i = fin.read(); // Store the character code
      if (i != -1) { // If there is file to read
      	System.out.println((char) i); // Cast the unicode code to a char value and print it
      }
   } while (i != -1); // When the iterator (character code) is -1 means that the file has ended
} catch (IOException e) {
   e.printStackTrace();
}
```

-  FileOutputStream (**write**): `new FileOutputStream("output_file_path");`

   This object allows us to write a new file. To write content inside the new file, there are two possibilities: read and write simultaneously or store the reading process inside a String variable and then write it.

##### Read and write

```java
try (FileInputStream fin = new FileInputStream("files/input.txt");
      FileOutputStream fos = new FileOutputStream("files/output_byteStream2.txt")) { // Create FileOutputStream to write the String content
   int i; // This variable will store the ascii/unicode character code of every character in the read file
   do { // Loop through all char
        i = fin.read(); // Store the character code
      if (i != -1) { // If there is file to read
      fos.write((char) i); // Cast the unicode code to a char value and concatenate to the string
      }
   } while (i != -1); // When the iterator (character code) is -1 means that the file has ended

   System.out.println("File created successfully");
} catch (IOException e) {
   e.printStackTrace();
}
```

##### Store in String and then write

```java
String text = "";

if (text.equals("")) { // Check if the text is empty
   System.out.println("Please first read the file");
   break;
} else { // Text has been set
   try (FileOutputStream fos = new FileOutputStream("files/output_byteStream.txt")) { // Create FileOutputStream to write the String content
      byte[] bytes = text.getBytes(); // Get bytes list from String
         fos.write(bytes); // Write content
         System.out.println("File created successfully");
   } catch (IOException e) {
      e.printStackTrace();
   }
}
```

## Charachter Stream

-  FileReader (**read**): `new FileReader("file_path");`

   This object allows us to read the file content. It reads every character as an unicode/ascii code (int) until it turns -1, that means that there is nothing else to read.

```java
try {
   FileReader reader = new FileReader("files/input.txt"); // Create FileReader
   int data = reader.read(); // Read data from file -> Again this stores the unicode/ascii character code

   while (data != -1) { // If data == -1 means that file has ended
      text += (char) data; // Concatenate the character to the String variable
      data = reader.read(); // This line allows to read the next character until it ends
   }

   reader.close(); // Close reader
} catch (Exception e) {
   e.printStackTrace();
}
```

-  FileWriter (**write**): `new FileWriter("output_file_path");`

   This object allows us to write a new file. To write content inside the new file, there are two possibilities: read and write simultaneously or store the reading process inside a String variable and then write it. This time the only method shown is trhough a String variable.

```java
// This example will only cover the write text methodology
String text = "";
// Instanciate the writer -> This is for the append option, if it's not defined like this the file will be overwritten
FileWriter writer = new FileWriter("files/output_characterStream.txt");

if (text.equals("")) { // Check if the text is empty
   System.out.println("Please first read the file");
   break;
} else { // Text has been set
   try {
      writer.write(text); // Write String content in the file
      // ? (notice that since writer is defined at the begining, the file is created at first)
      System.out.println("File created successfully");

		// Append new content
		String appendContent = "\n This text is appended to the file";
		writer.append(appendContent);
		System.out.println("Append successful!");
   } catch (Exception e) {
      System.out.println(e);
   }
}
```

## Buffered Stream

The difference between this way to read&write files is that we create a buffer memory to store the input/output instead of writing it directly to the disk.

-  BufferedReader (**read**): `new BufferedReader(new FileReader("file_path"));`

   This object allows us to read the file content. It reads every character as a string

```java
try {
   FileReader reader = new FileReader("files/input.txt"); // Create FileReader
   int data = reader.read(); // Read data from file -> Again this stores the unicode/ascii character code

   while (data != -1) { // If data == -1 means that file has ended
      text += (char) data; // Concatenate the character to the String variable
      data = reader.read(); // This line allows to read the next character until it ends
   }

   reader.close(); // Close reader
} catch (Exception e) {
   e.printStackTrace();
}
```

-  BufferedWriter (**write**): `new BufferedReader(new FileWriter("output_file_path"));`

```java
// This example will only cover the write text methodology
String text = "";

if (text.equals("")) { // Check if the text is empty
   System.out.println("Please first read the file");
   break;
} else { // Text has been set
   try (BufferedWriter bw = new BufferedWriter(new FileWriter("files/output_bufferedStream.txt"));) { // Create the BufferedReader inside the Try-Catch block
      bw.write(text); // Write String variable content

      System.out.println("File created successfully");
      bw.close(); // Close writer
   } catch (Exception e) {
      System.out.println(e);
   }
}
```
