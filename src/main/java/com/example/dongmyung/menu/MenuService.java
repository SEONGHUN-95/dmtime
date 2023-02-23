package com.example.dongmyung.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MenuService {
    @Value("${file.dir}")
    private String fileDir;

    private final MenuRepository menuRepository;

    public Page<Menu> getList(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page,1, Sort.by(sorts));
        return this.menuRepository.findAll(pageable);
    }
    public Long saveFile(MultipartFile files) throws IOException {
        if (files.isEmpty()){
            return null;
        }
        String origName = files.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String extension = origName.substring(origName.lastIndexOf("."));
        String savedName = uuid + extension;
        String savedPath = fileDir + savedName;
        LocalDateTime localDateTime = LocalDateTime.now();
        Menu menu = Menu.builder().originFileName(origName).storeFileName(savedName).savedPath(savedPath).createDate(localDateTime).build();
        files.transferTo(new File(savedPath));
        Menu savedFile = menuRepository.save(menu);
        return savedFile.getId();
    }
}
