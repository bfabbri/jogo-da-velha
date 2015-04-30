int pn = Integer.parseInt(args[0]);
        try
        {
            ServerSocket ss = new ServerSocket(pn);
            Socket sock = ss.accept();
            /*while((sock = ss.accept()) != null)
            {
                new Thread(new AlgumaThread(sock)).start();
            }*/

            BufferedReader br = new BufferedReader(
                new InputStreamReader(
                    sock.getInputStream()));
            PrintWriter pw = new PrintWriter(
                sock.getOutputStream(),true);
            String line;

            while((line = br.readLine()) != null)
            {
                System.out.println("Received: " + line);
                pw.println("You said: '" + line + "'!" );
            }
        }
        catch(IOException e)
        {
            System.out.println("Something Strange");
        }