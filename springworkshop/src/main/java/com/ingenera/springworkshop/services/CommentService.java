package com.ingenera.springworkshop.services;

import com.ingenera.springworkshop.models.bindmodels.ScoreBindModel;
import com.ingenera.springworkshop.models.entities.Comment;

public interface CommentService {

    Comment addNewComment(Long authorId, Long homeworkId, ScoreBindModel scoreBindModel);

}
