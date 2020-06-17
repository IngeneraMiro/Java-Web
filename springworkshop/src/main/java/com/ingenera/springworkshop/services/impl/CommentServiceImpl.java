package com.ingenera.springworkshop.services.impl;

import com.ingenera.springworkshop.models.bindmodels.ScoreBindModel;
import com.ingenera.springworkshop.models.entities.Comment;
import com.ingenera.springworkshop.repositories.CommentRepository;
import com.ingenera.springworkshop.services.CommentService;
import com.ingenera.springworkshop.services.HomeworkService;
import com.ingenera.springworkshop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final HomeworkService homeworkService;
    private final ModelMapper mapper;


    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, HomeworkService homeworkService, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.homeworkService = homeworkService;
        this.mapper = mapper;
    }


    @Override
    public Comment addNewComment(Long authorId, Long homeworkId, ScoreBindModel scoreBindModel) {
        Comment comment = new Comment();
        comment.setScore(Integer.parseInt(scoreBindModel.getScore()));
        comment.setTextContent(scoreBindModel.getTextContent());
        comment.setAuthor(userService.getUserById(authorId));
        comment.setHomework(this.homeworkService.getHomeworkById(homeworkId));
        this.commentRepository.saveAndFlush(comment);
        return null;
    }
}
