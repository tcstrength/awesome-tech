# Spring File Handling (Upload/Download)

Uploading/Downloading files, images is one of the most important parts in web application.

## Uploading files

```java
@RestController
@RequestMapping("/files")
public class FileController {
    @RequestMapping(value = "upload", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // Make sure that keys in form and @RequestParam are matching.
    public String uploadFile(@RequestParam("image")MultipartFile image) throws IOException {
        File convertFile = new File("/var/tmp/" + image.getOriginalFilename());
        FileOutputStream stream = new FileOutputStream(convertFile);
        // Get all bytes from the image and pass to the file.
        stream.write(image.getBytes());
        stream.close();
        return "File upload successfully";
    }
```

![File handling upload](images/spring-file-handling-upload.png)

## Downloading file

If the client can upload their files to our servers, they can also download something from us.

The downloading process a little bit complex, but it's not really hard to understand.

For downloading, we can use both InputStream and InputStreamResource but InputStream will be a fresh stream in each call, so Spring recommend us using InputStreamResource instead.

To make the browser understand that the content is downloadable, we must use __header Content-Disposition__.

```java
@RequestMapping(value = "download", method = RequestMethod.GET)
    public ResponseEntity<Object> downloadFile() throws FileNotFoundException {
        String fileName = "/var/tmp/test.png";
        File ourFile = new File(fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(ourFile));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", ourFile.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(ourFile.length())
                .contentType(MediaType.parseMediaType("application/txt"))
                .body(resource);
    }
```
