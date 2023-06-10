package ru.pda.cloudservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.pda.cloudservice.components.JwtUtils;
import ru.pda.cloudservice.entitys.FileList;
import ru.pda.cloudservice.entitys.UserFile;
import ru.pda.cloudservice.repositorys.FileRepository;
import ru.pda.cloudservice.repositorys.UserRepository;

import java.util.List;

@Service
public class CloudService {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileRepository fileRepository;

    public boolean uploadFile(String token, String fileName, byte[] file) throws RuntimeException{
        try {
            Long userId = userRepository.findByUsername(jwtUtils.getUsernameFromToken(token)).getId();
            fileRepository.save(new UserFile(userId, fileName, file));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public UserFile downloadFile(String token, String fileName) {
        Long userId = userRepository.findByUsername(jwtUtils.getUsernameFromToken(token)).getId();

        return fileRepository.findByUidAndFileName(userId, fileName);
    }

    public boolean updateFile(String token, String fileName, String name) throws RuntimeException{
        try {
            Long userId = userRepository.findByUsername(jwtUtils.getUsernameFromToken(token)).getId();
            UserFile userFile = fileRepository.findByUidAndFileName(userId, fileName);
            UserFile newUserFile = new UserFile(userId, name, userFile.getFileContent());

            if (userFile == null || name == null) return false;

            fileRepository.delete(userFile);
            fileRepository.save(newUserFile);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteFile(String token, String fileName) throws RuntimeException{
        try {
            Long userId = userRepository.findByUsername(jwtUtils.getUsernameFromToken(token)).getId();
            UserFile userFile = fileRepository.findByUidAndFileName(userId, fileName);
            if (userFile == null) return false;
            fileRepository.delete(userFile);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public List<Object> getFileList(int limit, String token) {
        Long userId = userRepository.findByUsername(jwtUtils.getUsernameFromToken(token)).getId();
        List<UserFile> userFiles = fileRepository.findFileNameByUid(userId, Pageable.ofSize(limit));
        List<Object> ufList = List.of(userFiles.stream().map(x-> {return new FileList(x.getFileName(), x.getFileSize()); }).toArray());

        return ufList;

    }
}
