package com.example.wordpressclone.application.dtos;

import java.time.LocalDate;
import com.example.wordpressclone.domain.entities.MediaItemEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaDTO {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("title")
    private String title;

    @JsonProperty("filename")
    private String filename;

    @JsonProperty("file_type")
    private String fileType;

    @NotNull
    @JsonProperty("url")
    private String url;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    @JsonProperty("upload_date")
    private LocalDate uploadDate;

    public static MediaDTO fromEntity(MediaItemEntity entity) {
        MediaDTO dto = new MediaDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setFilename(entity.getFilename());
        dto.setFileType(entity.getFileType());
        dto.setUrl(entity.getUrl());
        dto.setUploadDate(entity.getUploadDate());
        return dto;
    }

    public MediaItemEntity toEntity() {
        MediaItemEntity entity = new MediaItemEntity();
        entity.setId(this.id);
        entity.setTitle(this.title);
        entity.setFilename(this.filename);
        entity.setFileType(this.fileType);
        entity.setUrl(this.url);
        entity.setUploadDate(this.uploadDate);
        return entity;
    }
}