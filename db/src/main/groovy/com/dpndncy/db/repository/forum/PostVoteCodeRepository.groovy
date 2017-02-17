package com.dpndncy.db.repository.forum

import com.dpndncy.db.entity.forum.PostVoteCode
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by vaibhav on 17/02/17.
 */
interface PostVoteCodeRepository extends PagingAndSortingRepository<PostVoteCode, Long> {
    PostVoteCode findByDirection(Boolean direction);
}