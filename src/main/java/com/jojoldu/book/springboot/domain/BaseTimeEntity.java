package com.jojoldu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //jpa 엔티티 클래스들이 baseTimeEntity을 상속할 경우 필드들도 칼럼으로 인식하도록 함!
@EntityListeners(AuditingEntityListener.class) // Auditinf 기능을 포함시킴!!
public abstract  class BaseTimeEntity { //엔티티의 상위 클래스가 되어 엔티티들의 createdDate, modifiedDate를 자동으로 관리하는 역할!
    @CreatedDate //엔티티가 생성되어 저장될때 시간이 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회한 엔티티값을 변결할때 시간이 자동 저장
    private LocalDateTime modifiedDate;
}
