package com.example.seedcommando_9.finalwiktionary1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText searchWord;
    TextView showResult;
    String wordName,wikiAPIUrl,checkWordBR,urlResult="";
    String  send="";

    //added to git
    String main_result ="",result=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchWord = (EditText) findViewById(R.id.txt_search);//get id if word search filed
        showResult = (TextView) findViewById(R.id.txt_result);//get it of result text view

    }
    //onclick when click search button
    //Button  method
    public void show(View v) {

        wordName=searchWord.getText().toString().toLowerCase();//get word
        //   Toast.makeText(this,word,Toast.LENGTH_LONG).show();
        showResult.setText("");
        main_result="";
        send="";
        urlResult="";
        wikiAPIUrl="https://en.wiktionary.org/w/api.php?action=query&prop=revisions&titles="+wordName+"&rvprop=content&format=json&utf8=";
//

        //call asynctask method onpreexecute
        new getData().execute(wikiAPIUrl,"s",urlResult);
    }
    public class getData extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            showResult.setText("");
            main_result="";
            send="";


        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings)  {
            //  Log.i("search",word);

            try {
                URL url=new URL(wikiAPIUrl);
                //  Log.i("search1",word);
                HttpURLConnection con=(HttpURLConnection) url.openConnection();//open connection for url
                con.setRequestMethod("GET");//request method is get
                con.connect();
                BufferedReader bf= new BufferedReader(new InputStreamReader(con.getInputStream()));//read data as String
                while ((checkWordBR = bf.readLine()) != null)
                {
                    urlResult+=checkWordBR;

                }
                //Log.i("Result",jsd);
            }
            catch (Exception ex)
            {

            }
            return urlResult;//return to onpostexecute
        }

        @Override
        protected void onPostExecute(String jsd) {

            //  result=null;
            if(jsd.contains("===Noun==="))
            {
                int nounIndex=jsd.indexOf("===Noun===");
                String r1=jsd.substring(nounIndex);
                // System.out.println(r1);
                int i3=r1.indexOf("#");
                System.out.println(i3);
                String out=r1.substring(i3);
                //  System.out.println(out);
                //  System.out.println(out);
                if(out.contains("===")){
                    int i2=out.indexOf("===");
                    // System.out.println(i2);
                    String r2=out.substring(i2);//error
                    String main=r1.substring(i3, i3+i2);
                    //   System.out.println(main);
                    int i4=main.indexOf(".");
                    //  System.out.println(main.substring(0, i4+1));
                    result=main.substring(0, i4+1);
                }
                else{
                    int i4=out.indexOf(".");
                    //  System.out.println(main.substring(0, i4+1));
                    result=out.substring(0, i4+1);
                }
                dispaly(result,"Noun");
            }

            /*if(jsd.contains("===Noun==="))
            {   String result;
                int i1=jsd.indexOf("===Noun===");
                String r1=jsd.substring(i1);
                int i3=r1.indexOf("#");

                String out=r1.substring(i3);
                //  System.out.println(out);
                int i2=out.indexOf("===");
                //  System.out.println(i2);
                String r2=out.substring(i2);
                String main=r1.substring(i3, i3+i2);
                //   System.out.println(main);
                int i4=main.indexOf(".");
                //  System.out.println(main.substring(0, i4+1));
                result=main.substring(0, i4+1);
                dispaly(result,"Noun");
               // main_result+=send+"\n";
            }*/
            if(jsd.contains("===Adjective==="))
            {    String result;
                int adjectiveIndex=jsd.indexOf("===Adjective===");
                String r1=jsd.substring(adjectiveIndex);
                int i3=r1.indexOf("#");

                String out=r1.substring(i3);

              /*  int i2=out.indexOf("===");
                //    System.out.println(i2);
                String r2=out.substring(i2);
                String main=r1.substring(i3, i3+i2);

                int i4=main.indexOf(".");
                //  System.out.println(i4);
                //  System.out.println(main.substring(0, i4+1));
                result=main.substring(0, i4+1);*/
                if(out.contains("===")){
                    int i2=out.indexOf("===");
                    // System.out.println(i2);
                    String r2=out.substring(i2);//error
                    String main=r1.substring(i3, i3+i2);
                    //   System.out.println(main);
                    int i4=main.indexOf(".");
                    //  System.out.println(main.substring(0, i4+1));
                    result=main.substring(0, i4+1);
                }
                else{
                    int i4=out.indexOf(".");
                    //  System.out.println(main.substring(0, i4+1));
                    result=out.substring(0, i4+1);
                }
                dispaly(result,"\n Adjective");

            }
            if(jsd.contains("===Interjection==="))
            {   String result;
                int interjectionIndex=jsd.indexOf("===Interjection===");
                String r1=jsd.substring(interjectionIndex);
                int i3=r1.indexOf("|");

                String out=r1.substring(i3);
                //  System.out.println(out);
                if(out.contains("===")){
                    int i2=out.indexOf("===");

                    String r2=out.substring(i2);//error
                    String main=r1.substring(i3, i3+i2);

                    int i4=main.indexOf(".");

                    result=main.substring(0, i4+1);
                }
                else{
                    int i4=out.indexOf(".");

                    result=out.substring(0, i4+1);
                }

                dispaly(result,"Interjection");

            }


            main_result += send + "\n\n";
            main_result=main_result.trim();
            if(!main_result.isEmpty()) {
                //    main_result=main_result.replaceAll("\\s{2}"," ");
                //   System.out.println("Main " + main_result);
                showResult.setText(Html.fromHtml(main_result));
                //Store to DB
                new postData().execute("http://172.16.50.19:8080/api/status");//IP address

            }
            else
            {
                String wrong="<h3><font color='red'>Please Enter Correct Word</font></h3>";
                showResult.setText(Html.fromHtml(wrong));
            }



            // result1.setText("Wrong");

            //Toast.makeText(MainActivity.this,jsd,Toast.LENGTH_LONG).show();







        }
    }

    class postData extends AsyncTask<String,Void ,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings)   {
            try{
                return postData(strings[0]);
            }
            catch (IOException ex)
            {
                return "network error";
            }
            catch (JSONException rx)
            {
                return "invliad data";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this,"Inserted into database",Toast.LENGTH_LONG).show();
        }
        private String postData(String posturl) throws IOException,JSONException
        {
            BufferedWriter bw=null;
            BufferedReader br=null;
            StringBuilder result = new StringBuilder();
            try {


                JSONObject datasend = new JSONObject();
                datasend.put("word", wordName);
                datasend.put("meaning", main_result);
                URL url = new URL(posturl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.connect();
                //write data to database
                OutputStream outputStream = urlConnection.getOutputStream();
                bw = new BufferedWriter(new OutputStreamWriter(outputStream));
                bw.write(datasend.toString());
                bw.flush();
                //response from server
                InputStream is = urlConnection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = br.readLine()) != null) {
                    result.append(line).append("\n");
                }
            }
            finally {
                //Connection close
                if(br!=null)
                {
                    br.close();
                }
                if(bw!=null)
                {
                    bw.close();
                }
            }
            return result.toString();
        }
    }

    //Proper noun changes wiktionary
    public  String dispaly(String result, String which) {
        String fword="";
        String dump=result;
        Log.i("result "+which,result);
        if(result!=null){
            result=result.replaceAll("lang=en","");
            String ts;
            if(result.contains("[[")||result.contains("{{"))
            {	dump=dump.replaceAll("[^a-zA-Z0-9.\\[\\]|,;}\\s+]", "").trim();
                if(dump.startsWith("[["))
                {
                    int i=result.indexOf("[[");
                    int j=result.indexOf("]]");
                    fword=result.substring(i,j);
                    int k=fword.indexOf('|');
                    fword=fword.substring(k+1);
                    //System.out.println("First Word "+fword);
                    //System.out.println("FIND "+result);
                    result=result.substring(j+2);
                    //	System.out.println("FIND "+result);
                    result=fword.concat(result);
                    result=result.replace('|',' ');

                }
                else
                {
                    System.out.println("Esle called "+result);
                    dump=dump.replaceAll("[^a-zA-Z0-9.\\[\\]|,;}\\s+]", "").trim();
                    String arr[]=dump.split(" ");
                    for(int aa=0;aa<arr.length;aa++)
                    {

                        if(arr[aa].contains("[[")&&arr[aa].contains("|"))
                        {
                            //System.out.println(arr[aa]);
                            String min=arr[aa];
                            //System.out.println("MINNN "+min);
                            int x=min.indexOf("[[");
                            int y=min.indexOf("|");
                            min=min.substring(y+1);
                            //System.out.println(min);
                            result=result.replace(arr[aa], min);
                        }
                    }
                    result=result.replace('|',' ');
                }
            }
            //if(result.matches("[[[a-zA-Z|]"))
            send+="<h3>"+which+"</h3>"+"\n";
            //   System.out.println("Meaning for "+which);
            if(result.contains("}}"))
            {
                int resultindex=result.indexOf("}}");
                //	 System.out.println(resultindex);
                result=result.substring(resultindex);
            }

            result = result.replaceAll("[^a-zA-Z0-9.,;\\s+]", "");
            result=result.replaceAll(";",".\n");

            //  result.endsWith(".");
            // System.out.println(result);
            int newline=0;
            for(int i=0;i<result.length();i++)
            {
                if(result.charAt(i)=='\n')
                {
                    newline++;
                }
            }

            // System.out.println("New Line "+newline);
            for(int i=0;i<newline;i++)
            {
                int nn=result.indexOf("\n");
                // System.out.println("First line "+nn);
                String res=result.substring(0,nn);
                result=result.substring(nn+1);
                // System.out.println(res);
                send+=res+"\n";
            }
            int nn=result.indexOf("\n");


            result=result.substring(nn+1);
            send+=result+"\n";
            // System.out.println(result);
          /*  fword=fword.trim();
                if(!fword.isEmpty())
                {

                    fword=fword.concat(" ");
                   // send=fword+send;
                   send=fword.concat(send);

                   // System.out.println("Called");
                    return send;
                }*/



        }
        /*else {
            result1.setText("Wrorng");
            send+="Enter Correct word";
           // System.out.println("Enter correct word");
            return "Wrong";
        }*/

        return send;
    }
}
