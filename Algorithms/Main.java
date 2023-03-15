import java.util.*;


public class Main 
{
    public static void main(String[] args) 
    {
        String a = "Hello       World!";
        String b ="""
                Team games wins drews losses,
                A,2,2,2,2
                B,2,2,2,2
                """;
        String[] parts = b.split("\\s+");
        for(int i=1;i<parts.length;i++)
        {
            System.out.println(parts[i]);


        }
    }
}


