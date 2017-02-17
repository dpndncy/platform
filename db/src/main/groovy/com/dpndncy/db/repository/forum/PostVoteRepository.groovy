package com.dpndncy.db.repository.forum

import com.dpndncy.db.entity.forum.PostVote
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by vaibhav on 17/02/17.
 */
interface PostVoteRepository extends PagingAndSortingRepository<PostVote, Long> {
    PostVote findByUserIdAndPostId(Long userId, Long postId);
}