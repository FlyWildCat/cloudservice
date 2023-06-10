package ru.pda.cloudservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.pda.cloudservice.entitys.FileName;
import ru.pda.cloudservice.entitys.UserFile;
import ru.pda.cloudservice.services.CloudService;

import java.io.IOException;
import java.util.List;

@RestController
public class FileController {
    @Autowired
    private CloudService cloudService;

    @PostMapping("/file")
    public ResponseEntity<?> uploadFile(@RequestParam("filename") String filename,
                                        @RequestHeader("auth-token") String authtoken,
                                        @RequestParam("file") MultipartFile file) throws IOException {
        if (!cloudService.uploadFile(authtoken.substring(7), filename, file.getBytes()))
            return ResponseEntity.ok().body("file not upload");

        return ResponseEntity.ok().body("file upload");
    }

    @GetMapping(value = "/file", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> downloadFile(@RequestParam String filename, @RequestHeader("auth-token") String authtoken) {
        UserFile userFile = cloudService.downloadFile(authtoken.substring(7), filename);

        if (userFile.getFileName() == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .contentType(MediaType.MULTIPART_FORM_DATA)  //.parseMediaType(fileName.split("[.]")[1])
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + userFile.getFileName() + "\"")
                .body(userFile.getFileContent());
    }

    @PutMapping("/file")
    public ResponseEntity<?> updateFile(@RequestParam String filename, @RequestHeader("auth-token") String authtoken,
                                        @RequestBody FileName newfilename) throws IOException{

        if (!cloudService.updateFile(authtoken.substring(7), filename, newfilename.getFileName()))
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok("file updated");
    }

    @DeleteMapping("/file")
    public ResponseEntity<?> deleteFile(@RequestParam String filename, @RequestHeader("auth-token") String authtoken) {
        if (!cloudService.deleteFile(authtoken.substring(7), filename))
            return ResponseEntity.ok("file not deleted");

        return ResponseEntity.ok("file deleted");
    }

    @GetMapping("/list")
    public ResponseEntity<?> getFileList(@RequestParam int limit, @RequestHeader("auth-token") String authtoken) {
        List<Object> fileNameList = cloudService.getFileList(limit, authtoken.substring(7));

        return ResponseEntity.ok(fileNameList);
    }
}