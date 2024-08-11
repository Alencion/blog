package com.alencion.blog.adaptor.out.filesystem.post;

import com.alencion.blog.post.Post;
import com.alencion.blog.post.PostMeta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface PostCommonAdaptor {

    String SPLIT_PATTERN = "\n={5,}\n";
    TypeReference<PostMeta> POST_META_TYPE = new TypeReference<>() {};

    ObjectMapper getObjectMapper();

    default Post parsePost(String rawPost) {
        PostMeta meta = parsePostMeta(rawPost);
        String content = parsePostContent(rawPost);
        return Post.of(meta, content);
    }

    default PostMeta parsePostMeta(String rawPost) {
        String[] split = rawPost.split(SPLIT_PATTERN);
        if (split.length != 2) {
            throw new IllegalStateException("not fave meta data.");
        }

        try {
            return getObjectMapper().readValue(split[0], POST_META_TYPE);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    default String parsePostContent(String rawPost) {
        String[] split = rawPost.split(SPLIT_PATTERN);
        if (split.length != 2) {
            throw new IllegalStateException("not fave meta data.");
        }

        return split[1];
    }
}
