package edu.noia.myoffice.common.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.DefaultRevisionEntity;
import org.springframework.data.envers.repository.support.DefaultRevisionMetadata;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor(staticName = "of")
public class RevisionDto<T> {

    @JsonIgnore
    @NonNull
    Revision<Integer, T> revision;

    public static List<RevisionDto> from(Revisions revisions) {
        List<Revision<Integer, ?>> rl = revisions.getContent();
        return rl.stream().map(RevisionDto::of).collect(toList());
    }

    public T getEntity() {
        return revision.getEntity();
    }

    public Integer getRevisionNumber() {
        return revision.getRevisionNumber().orElse(null);
    }

    public LocalDateTime getRevisionDate() {
        DefaultRevisionMetadata revisionMetadata = (DefaultRevisionMetadata) (revision.getMetadata());
        DefaultRevisionEntity revisionEntity = revisionMetadata.getDelegate();
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(revisionEntity.getTimestamp()), ZoneOffset.UTC);
    }
}
