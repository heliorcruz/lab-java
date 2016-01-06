<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>

	<div id="container">
		<div id="content">
			<div class="card">
				<div class="image">
					<a href="profile/'+id+'"><img src="img/profile.jpg"></a> <span
						class="title">${user.name}</span>
				</div>
				<div class="content">
					<p>${user.lastName}</p>
					<p>${user.birthDate}</p>
					<p>${user.email}</p>
				</div>
				<div class="action">
					<a href="PostList.action">My POSTS</a>
				</div>
				<div class="action">
					<a href="FriendList.action">FRIENDS (${user.numFriends}) </a>
				</div>
				<div class="action">
					<a href="User.action?user.id=${user.id}">PROFILE</a>
														
					<form class="login" action="logout" method="get">
					<div class="btn-logout" align="right" >																	
					<input class="b" type="submit" value="Logout!" name="btn-logout"></div>							
					</form>
				</div>
			</div>
		</div>

		<div id="sidebar">

			<form class="panel" action="PostForm.action" method="get">
				<div class="post">
					<div class="action">
						<a href="">NEW POST: </a><input type="hidden"
							name="userId" value="${user.id}"> 
							<textarea rows="2" cols="65" name="new_topic">${edit_post.topic}
						    </textarea>					
					</div>
					<div class="content">
						<textarea rows="4" cols="75" name="new_message">${edit_post.message}
						</textarea>
					</div>
					<div class="action">
						<p>&nbsp&nbsp&nbsp
						    <input type="hidden" name="type" value="user_posts">
						    <input type="hidden" name="postIdUpdate" value="${edit_post.id}">	
						    <div class="btn-save" align="right" >					    
							<input class="b" type="submit" value="Save" name="btn-save" id="save">
							</div>
					</div>
				</div>
			</form>


			<c:forEach var="postItem" items="${post_list}">
				<div class="post">
					<div class="action">
						<a href="CommentList.action?&postIdComment=${postItem.id}">${postItem.topic}</a>						
						<a href="" style="float:right;">${postItem.date}</a>
					</div>
					<div class="content">
						<p>${postItem.message}</p>
					</div>
					<div class="action">
						<a href="CommentList.action?type=user_posts&postIdComment=${postItem.id}">COMMENTS (${postItem.numComments})</a>												
						<br>	
						<form class="comment" action="CommentForm.action" method="get">
						    <input type="hidden" name="type" value="user_posts">
							<input type="hidden" name="userId" value="${user.id}"> <input
								type="hidden" name="postIdComment" value="${postItem.id}">
							<textarea rows="2" cols="75" name="new_comment"></textarea>							
							<div class="btn-add" align="right" >
							<input class="b" type="submit" value="Add" name="btn-add"></div>						
						</form>						
						
					</div>
					<div class="action">
						<a href="Post.action?postIdUpdate=${postItem.id}">Edit</a>						
						<a href="PostForm.action?type=user_posts&postIdDelete=${postItem.id}">Delete</a>						
					    <a style="float:right;"	href="LikeList.action?type=user_posts&postIdLike=${postItem.id}">Likes:${postItem.numLikes}</a>										 
					    <div align="right">
						<form class="like" action="LikeForm.action" method="get">
							<input type="hidden" name="userId" value="${user.id}"> 
							<input type="hidden" name="type" value="user_posts"> 
							<input type="hidden" name="idNewLikePost" value="${postItem.id}">							
							<input class="b" type="submit" value="Like + " name="btn-like">							
						</form></div>
					</div>
				</div>
			</c:forEach>
			
			<c:forEach var="postItem" items="${friends_posts}">
				<div class="post">
					<div class="action">
						<a href="CommentList.action?&postIdComment=${postItem.id}">${postItem.topic}</a>												
						 <a	style="float:right;" href="">${postItem.date}</a>
						 <a style="float:right;" href="">${postItem.user.name}</a>
					</div>
					<div class="content">
						<p>${postItem.message}</p>
					</div>
					<div class="action">
						<a href="CommentList.action?type=friends_posts&postIdComment=${postItem.id}">COMMENTS</a>
						<a href="">${postItem.numComments}</a>						
						<div align="right">	
						<form class="comment" action="CommentForm.action" method="get">
							<input type="hidden" name="type" value="friends_posts">
							<input type="hidden" name="userId" value="${user.id}"> <input
								type="hidden" name="postIdComment" value="${postItem.id}">
							<textarea rows="2" cols="75" name="new_comment"></textarea>
							<input class="b" type="submit" value="Add" name="btn-add">						
						</form></div>						
						
					</div>
					<div class="action">
					    <a style="float:left;" href="LikeList.action?type=friends_posts&postIdLike=${postItem.id}">Likes:${postItem.numLikes}</a>
					    <p>											 
					    <div align="right">
						<form class="like" action="LikeForm.action" method="get">
							<input type="hidden" name="userId" value="${user.id}">
							<input type="hidden" name="type" value="friends_posts">
							 <input	type="hidden" name="idNewLikePost" value="${postItem.id}">							
							 <input class="b" type="submit" value="Like + " name="btn-like">							
						</form></div>
					</div>
				</div>
			</c:forEach>			

		</div>

		<div id="groupbar">					

			<c:forEach var="commItem" items="${comment_list}">
				<div class="card">
					<div class="action">
						<a href="">${commItem.user.name}</a>
						 <a style="float:right;"	href="">${commItem.date}</a>
					</div>
					<div class="content">
						<p>${commItem.message}</p>
					</div>
					<div class="action">
						<a	href="">Likes:${commItem.numLikes}</a>					
						<a style="float:right;" href="CommentForm.action?commIdDelete=${commItem.id}">Delete</a>
						 <form class="like" action="LikeForm.action" method="get">
							<input type="hidden" name="userId" value="${user.id}"> <input
								type="hidden" name="idNewLikeComment" value="${commItem.id}">							
							&nbsp&nbsp<input class="b" type="submit" value="Like + " name="btn-like">							
						</form>
					</div>
				</div>
			</c:forEach>

			<c:forEach var="likeItem" items="${like_list}">
				<div class="card">
					<div class="action">
						<a href="">${likeItem.user.name} ${likeItem.user.lastName}</a>
					</div>
				</div>
			</c:forEach>
			
			<c:forEach var="friendItem" items="${friends}">
				<div class="card">
					<div class="action">
						<a href="">${friendItem.name} ${friendItem.lastName}</a>					
					<a style="float:right;" href="ProfileServlet.action?friendId=${friendItem.id}">PROFILE</a>
					</div>					
				</div>
			</c:forEach>
			
			<c:if test="${not empty friend}">				
				<div class="card">
				<div class="image">
					<a href="profile/'+id+'"><img src="img/profile.jpg"></a> <span
						class="title">${friend.name}</span>
				</div>
				<div class="content">
					<p>${friend.lastName}</p>
					<p>${friend.birthDate}</p>
					<p>${friend.email}</p>
				</div>			
										
				<div class="action">
					<a href="ProfileServlet.action?type=friend&friendId=${friend.id}">POSTS</a>	
					<div align="right">
					<form class="like" action="ProfileServlet.action" method="get">
						<input type="hidden" name="friendId" value="${friend.id}">												
						<input class="b" type="submit" value="Delete" name="btn-delete">							
					</form>
				</div>			
				</div>
				
				 
			</div>
			</c:if>
			
		</div>
	</div>


</body>
</html>