import java.io.*;
import java.util.*;

public class HW1
{
    public static void main(String[] args)
    {
        Scanner inputStream = null;
        try
        {
            inputStream = new Scanner(new FileInputStream("inputFile.txt"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found.");
            System.exit(0);
        }
        
        ArrayList<String> sentences = new ArrayList<String>();
        while(inputStream.hasNextLine())
        {
            String line = inputStream.nextLine().toLowerCase();
            if(line.contains("."))
            {
                String[] sentenceSplit = line.split("\\.");
                for(int x = 0; x < sentenceSplit.length; x++)
                {
                    sentences.add(sentenceSplit[x].trim());
                }
            }
            else
            {
                sentences.add(line);
            }
        }
        inputStream.close();
        
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<String> eachWord = new ArrayList<String>();
        for(String s: sentences)
        {
            String[] wordsInSentences = s.split("\\s+");
            for(int a = 0; a < wordsInSentences.length; a++)
            {
                words.add(wordsInSentences[a]);
                if(!eachWord.contains(wordsInSentences[a]))
                {
                    eachWord.add(wordsInSentences[a]);
                }
            }
        }
        
        Formatter file;
        PrintWriter pw = null;
        String word = "";
        for(int x = 1; x < 10; x++)
        {
            int frequency = 0;
            int count = 0;
            try
            {
                file = new Formatter("File" + Integer.toString(x) + ".txt");
                pw = new PrintWriter(new FileOutputStream("File" + Integer.toString(x) + ".txt"));
                switch(x)
                {
                    case 1: //most frequent word
                    /*
                    ArrayList<String> mostFrequentWords = new ArrayList<String>();
                    frequency = 0;
                    for(int y = 0; y < words.size(); y++)
                    {
                        count = 0;
                        for(int z = 0; z < words.size(); z++)
                        {
                            if(words.get(y).equals(words.get(z)))
                            {
                                count++;
                            }
                        }
                        
                        if(count > frequency)
                        {
                            if(!mostFrequentWords.contains(words.get(y)))
                            {
                                mostFrequentWords.clear();
                                mostFrequentWords.add(words.get(y));
                                frequency = count;
                            }
                        }
                        else if(count == frequency)
                        {
                            if(!mostFrequentWords.contains(words.get(y)))
                            {
                                mostFrequentWords.add(words.get(y));
                            }
                        }
                    }
                    
                    for(String s: mostFrequentWords)
                    {
                        pw.println(s + ":" + frequency);
                    }
                    pw.close();
                    break;
                    */
                    
                    case 2: //third most frequent word
                    int max = Integer.MIN_VALUE;
                    int theMax = Integer.MAX_VALUE;
                    ArrayList<Integer> frequencyOfEachWord = new ArrayList<Integer>();
                    for(String s: eachWord)
                    {
                        count = 0;
                        for(String w: words)
                        {
                            if(s.equals(w))
                            {
                                count++;
                            }
                        }
                        frequencyOfEachWord.add(count);
                    }
                    
                    for(int a = 0; a < 3; a++)
                    {
                        max = Integer.MIN_VALUE;
                        for(int i: frequencyOfEachWord)
                        {
                            if(max < i && i < theMax)
                            {
                                max = i;
                            }
                        }
                        theMax = max;
                        
                        if(x == 1 && a == 0)
                        {
                            for(int b = 0; b < frequencyOfEachWord.size(); b++)
                            {
                                if(frequencyOfEachWord.get(b) == max)
                                {
                                    pw.println(eachWord.get(b) + ":" + max);
                                }
                            }
                        }
                        else if(x == 2 && a == 2)
                        {
                            for(int b = 0; b < frequencyOfEachWord.size(); b++)
                            {
                                if(frequencyOfEachWord.get(b) == max)
                                {
                                    pw.println(eachWord.get(b) + ":" + max);
                                }
                            }
                        }
                    }
                    break;
                    
                    case 3: //highest frequency in a sentence
                    ArrayList<String> HighestFrequency = new ArrayList<String>();
                    frequency = 0;
                    for(String s: sentences)
                    {
                        String[] wordsInSentence = s.split("\\s+");
                        ArrayList<String> uniqueWordsInSentence = new ArrayList<String>();
                        for(int a = 0; a < wordsInSentence.length; a++)
                        {
                            if(!uniqueWordsInSentence.contains(wordsInSentence[a]))
                            {
                                uniqueWordsInSentence.add(wordsInSentence[a]);
                            }
                        }
                        
                        for(int b = 0; b < uniqueWordsInSentence.size(); b++)
                        {
                            count = 0;
                            for(int c = 0; c < wordsInSentence.length; c++)
                            {
                                if(uniqueWordsInSentence.get(b).equals(wordsInSentence[c]))
                                {
                                    count++;
                                }
                            }
                            
                            if(count > frequency)
                            {
                                HighestFrequency.clear();
                                HighestFrequency.add(uniqueWordsInSentence.get(b));
                                HighestFrequency.add(s);
                                frequency = count;
                            }
                            else if(count == frequency)
                            {
                                HighestFrequency.add(uniqueWordsInSentence.get(b));
                                HighestFrequency.add(s);
                            }
                        }
                    }
                    
                    if(HighestFrequency.size() != 0)
                    {
                        for(int s = 0; s < HighestFrequency.size(); s+=2)
                        {
                            pw.println(HighestFrequency.get(s) + ":" + frequency + ":"+ HighestFrequency.get(s + 1));
                        }
                    }
                    break;
                    
                    case 4: //"the"
                    word = "the";
                    break;
                    
                    case 5: //"of"
                    word = "of";
                    break;
                    
                    case 6: //"was"
                    word = "was";
                    break;
                    
                    case 7: //"but the"
                    word = "but the";
                    break;
                    
                    case 8: //"it was"
                    word = "it was";
                    break;
                    
                    case 9: //"in my"
                    word = "in my";
                    break;
                }
            }
            catch(FileNotFoundException e)
            {
                System.out.println("File not found.");
                System.exit(0);
            }
            
            if(x > 3 && x < 10)
            {
                ArrayList<String> sentenceWithWord = new ArrayList<String>();
                for(String s: sentences)
                {
                    count = 0;
                    if(!s.contains(" "))
                    {
                        if(s.equals(word))
                        {
                            count++;
                        }
                    }
                    else
                    {
                        for(int z = 0; z < s.length() - word.length(); z++)
                        {
                            if(s.substring(z, z + word.length()).equals(word))
                            {
                                if(z == 0 && s.charAt(z + word.length()) == ' ' || // "word "
                                   z == s.length() - word.length() - 1 && s.charAt(z - 1) == ' ') // " word"
                                {
                                    count++;
                                }
                                else if(z > 0 && s.charAt(z - 1) == ' ' && s.charAt(z + word.length()) == ' ') // " word "
                                {   
                                    count++;
                                }
                            }
                        }
                    }
                            
                    if(count > frequency)
                    {
                        sentenceWithWord.clear();
                        sentenceWithWord.add(s);
                        frequency = count;
                    }
                    else if(count == frequency)
                    {
                        sentenceWithWord.add(s);
                    }
                }
                    
                for(String s: sentenceWithWord)
                {
                    pw.println(word + ":" + frequency + ":" + s);
                }
            }
            pw.close();
        }
    }
}