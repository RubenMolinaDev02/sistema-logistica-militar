package com.example.weapon_microservice.model.attachment;
import com.example.weapon_microservice.model.attachment.enums.AttachmentType;
import com.example.weapon_microservice.model.common.enums.MountType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "attachment")
@Builder
@Getter
@Setter
public class AttachmentModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private String image;
    private MountType mountType;
    private AttachmentType type;
}
