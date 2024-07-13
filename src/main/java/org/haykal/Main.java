package org.haykal;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1 && args.length != 2) {
            System.out.println("Usage: java Main -c|-l|-w|-m <file_path>");
            return;
        }

        String option = "";
        String filePath = null;

        if (args.length == 2) {
            option = args[0];
            filePath = args[1];
        } else if (args.length == 1) {
            if (args[0].startsWith("-")) {
                option = args[0];
            } else {
                filePath = args[0];
            }
        }

        if (filePath != null) {
            File file = new File(filePath);
            if (!file.exists() || !file.isFile()) {
                System.out.println("Error: Cannot open file " + filePath);
                return;
            }
        }

        switch (option) {
            case "-c":
                if (filePath != null) {
                    countBytes(new File(filePath));
                } else {
                    countBytes(System.in);
                }
                break;
            case "-l":
                if (filePath != null) {
                    countLines(new File(filePath));
                } else {
                    countLines(System.in);
                }
                break;
            case "-w":
                if (filePath != null) {
                    countWords(new File(filePath));
                } else {
                    countWords(System.in);
                }
                break;
            case "-m":
                if (filePath != null) {
                    countCharacters(new File(filePath));
                } else {
                    countCharacters(System.in);
                }
                break;
            default:
                if (filePath != null) {
                    countAll(new File(filePath));
                } else {
                    countAll(System.in);
                }
                break;
        }
    }

    private static void countBytes(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            long byteCount = file.length();
            System.out.println(byteCount + " " + file.getPath());
        } catch (IOException e) {
            System.out.println("Error: Cannot read file " + file.getPath());
        }
    }

    private static void countBytes(InputStream in) {
        try {
            long byteCount = 0;
            int read;
            while ((read = in.read()) != -1) {
                byteCount++;
            }
            System.out.println(byteCount + " [stdin]");
        } catch (IOException e) {
            System.out.println("Error: Cannot read from stdin");
        }
    }

    private static void countLines(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            long lineCount = reader.lines().count();
            System.out.println(lineCount + " " + file.getPath());
        } catch (IOException e) {
            System.out.println("Error: Cannot read file " + file.getPath());
        }
    }

    private static void countLines(InputStream in) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            long lineCount = reader.lines().count();
            System.out.println(lineCount + " [stdin]");
        } catch (IOException e) {
            System.out.println("Error: Cannot read from stdin");
        }
    }

    private static void countWords(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            long wordCount = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
            System.out.println(wordCount + " " + file.getPath());
        } catch (IOException e) {
            System.out.println("Error: Cannot read file " + file.getPath());
        }
    }

    private static void countWords(InputStream in) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            long wordCount = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
            System.out.println(wordCount + " [stdin]");
        } catch (IOException e) {
            System.out.println("Error: Cannot read from stdin");
        }
    }

    private static void countCharacters(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            long charCount = 0;
            int character;
            while ((character = reader.read()) != -1) {
                charCount++;
            }
            System.out.println(charCount + " " + file.getPath());
        } catch (IOException e) {
            System.out.println("Error: Cannot read file " + file.getPath());
        }
    }

    private static void countCharacters(InputStream in) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            long charCount = 0;
            int character;
            while ((character = reader.read()) != -1) {
                charCount++;
            }
            System.out.println(charCount + " [stdin]");
        } catch (IOException e) {
            System.out.println("Error: Cannot read from stdin");
        }
    }

    private static void countAll(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            long byteCount = file.length();
            long lineCount = 0;
            long wordCount = 0;
            long charCount = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                charCount += line.length() + System.lineSeparator().length();
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
            System.out.println(byteCount + " " + wordCount + " " + lineCount + " " + file.getPath());
        } catch (IOException e) {
            System.out.println("Error: Cannot read file " + file.getPath());
        }
    }

    private static void countAll(InputStream in) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            long byteCount = 0;
            long lineCount = 0;
            long wordCount = 0;
            long charCount = 0;

            String line;
            int read;
            while ((read = in.read()) != -1) {
                byteCount++;
            }
            in.reset();

            while ((line = reader.readLine()) != null) {
                lineCount++;
                charCount += line.length() + System.lineSeparator().length();
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
            System.out.println(byteCount + " " + wordCount + " " + lineCount + " [stdin]");
        } catch (IOException e) {
            System.out.println("Error: Cannot read from stdin");
        }
    }
}