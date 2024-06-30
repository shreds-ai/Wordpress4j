package com.example.application.ports;

import java.util.List;
import com.example.application.dtos.PostDetailsDTO;
import org.springframework.dao.DataAccessException;
import java.lang.IllegalArgumentException;

/**
 * Input contract for the PostListUseCase, receiving parameters from the primary adapter and passing them to the use case.
 * This interface defines the method to fetch posts based on various filtering criteria.
 *
 * @param context Scope under which the request is made; determines fields present in response.
 * @param page Current page of the collection.
 * @param perPage Maximum number of items to be returned in result set.
 * @param search Limit results to those matching a string.
 * @param after Limit response to posts published after a given ISO8601 compliant date.
 * @param modifiedAfter Limit response to posts modified after a given ISO8601 compliant date.
 * @param author List of author IDs to include in the results.
 * @param authorExclude List of author IDs to exclude from the results.
 * @param before Limit response to posts published before a given ISO8601 compliant date.
 * @param modifiedBefore Limit response to posts modified before a given ISO8601 compliant date.
 * @param exclude List of post IDs to exclude from the results.
 * @param include List of post IDs to include in the results.
 * @param offset Number of posts to skip in the result set.
 * @param order Order direction of the results (asc or desc).
 * @param orderBy Attribute by which to order the results.
 * @param slug List of slugs to filter the results.
 * @param status List of post statuses to filter the results.
 * @param categories List of category IDs to include in the results.
 * @param categoriesExclude List of category IDs to exclude from the results.
 * @param tags List of tag IDs to include in the results.
 * @param tagsExclude List of tag IDs to exclude from the results.
 * @param sticky Whether to filter for sticky posts only.
 * @throws DataAccessException if there is a database access error.
 * @throws IllegalArgumentException if input parameters are invalid.
 * @return List of PostDetailsDTO representing the filtered posts.
 */
public interface PostListInputPort {
    List<PostDetailsDTO> fetchPosts(String context, Integer page, Integer perPage, String search, String after, String modifiedAfter, List<Integer> author, List<Integer> authorExclude, String before, String modifiedBefore, List<Integer> exclude, List<Integer> include, Integer offset, String order, String orderBy, List<String> slug, List<String> status, List<Integer> categories, List<Integer> categoriesExclude, List<Integer> tags, List<Integer> tagsExclude, Boolean sticky) throws DataAccessException, IllegalArgumentException;
}