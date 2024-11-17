package CS2Lab114.Lab9;
import java.io.*;
import java.util.Scanner;
public class Task1 {
    public static String getInputFileName(Scanner in)
    {
        String file;
        boolean res = false;
        do{
            System.out.print("Enter input file name> ");
            file = in.next()+".txt".toLowerCase();
            File f = new File(file);
            res = f.exists() && !(f.isDirectory());
        }while(!res);
        return file;
    }
    public static String getOutFileName(String input, Scanner in)
    {
        String file;
        boolean res = false;
        System.out.print("Enter output file name> ");
        file = in.next()+".txt".toLowerCase();
        do{
            if(!(file.equals(input)))
                res = true;
            else {
                System.out.println("Output file name should not be same as input file name");
                System.out.print("Enter the output file name again> ");
                file = in.next()+".txt".toLowerCase();
            }
        }while(!res);
        return file;
    }

    public static int getTheCount(String s)
    {
        int c = 0;
        String str[] = s.split(" ");
        for(int i = 0; i < str.length; i++)
            if(str[i].equalsIgnoreCase("the"))
                ++c;

        return c;
    }
    public static void CopyLines(String inputfile,String outputfile) throws IOException
    {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            //buffered I/O streams are efficient as the native input API is called only when the buffer is empty/full.
            in = new BufferedReader(new FileReader(inputfile));
            //PrintWriter is also buffered stream where object flushes the buffer on every invocation of println.
            out = new PrintWriter(new FileWriter(outputfile));
            int linesRead = 0;
            String s;
            int count = 0;
            //Invoking readLine returns a line of text which goes to output using println,
            //which appends the line terminator for the current operating system.
            //This might not be the same line terminator that was used in the input file.
            while ((s = in.readLine()) != null) {
                count = getTheCount(s);
                out.println(s+" ["+count+"]");//you may try print(s) without a new line if needed
                linesRead++;
            }
            System.out.println("Lines read = "+linesRead);
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        String inputFile, outputFile;
        Scanner in = new Scanner(System.in);
        inputFile = getInputFileName(in);
        outputFile = getOutFileName(inputFile,in);
        File result = new File(outputFile);
        boolean createFile = result.createNewFile();
        CopyLines(inputFile, outputFile);

    }
}