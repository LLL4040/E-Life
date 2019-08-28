import 'dart:convert';

import 'package:http/http.dart' as http;
class estateforumHttp {



  var getPostUrl= "http://zhimo.natapp1.cc/estateforum-server/api/post/findPost";

  var getCommentsUrl= "http://zhimo.natapp1.cc/estateforum-server/api/postComments/findComments";

  var addCommentUrl= "http://zhimo.natapp1.cc/estateforum-server/api/postComments/addComments";

  var deleteCommentUrl= "http://zhimo.natapp1.cc/estateforum-server/api/postComments/deleteComments";

  var getTabsUrl= "http://zhimo.natapp1.cc/estateforum-server/api/post/tags";
  var getPostByTabs= "http://zhimo.natapp1.cc/estateforum-server/api/post/findPostByTag";

  getPostList(NetListener net,String communityId,String page,String size,String session) {
    var client = new http.Client();
    client.post(
        getPostUrl,
        headers: {
          "cookie": session,
        },
        body: {
          "communityId": communityId,
          "page": page,
          "size": size,
        }
    ).then((
        response,
        ) {
      //print(response.body);
      //print(jsonDecode(response.body));
      List responseJson = json.decode(response.body);
      List<Post> post = responseJson.map((m) => new Post.fromJson(m)).toList();
      net.onAllPostResponse(post);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  getCommentList(NetListener net,String pid,String page,String size,String session) {
    var client = new http.Client();
    client.post(
        getCommentsUrl,
        headers: {
          "cookie": session,
        },
        body: {
          "pid": pid,
          "page": page,
          "size": size,
        }
    ).then((
        response,
        ) {
      //print(response.body);
      //print(jsonDecode(response.body));
      List responseJson = json.decode(response.body);
      List<Comment> comment = responseJson.map((m) => new Comment.fromJson(m)).toList();

      net.onAllCommentsResponse(comment);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  addComment(NetListener net,String pid, String commenterName, String postComment,String session){
    var client = new http.Client();
    client.post(
        addCommentUrl,
        headers: {
          "cookie": session,
        },
        body: {
          "pid": pid,
          "commenterName": commenterName,
          "postComment": postComment,
        }
    ).then((response,) {
      Map<String, dynamic> responseJson = json.decode(response.body);
      bool success = responseJson.containsValue("1");

      net.onAddComment(success);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  deleteComment(NetListener net,String pid, String location,String session){
    var client = new http.Client();
    client.post(
        deleteCommentUrl,
        headers: {
          "cookie": session,
        },
        body: {
          "pid": pid,
          "location": location,
        }
    ).then((response,) {
      Map<String, dynamic> responseJson = json.decode(response.body);
      bool success = responseJson.containsValue("1");
      net.onDeleteComment(success);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }
  getTabs(NetListener net,String communityId){
    var client = new http.Client();
    client.post(
        getTabsUrl,

        body: {
          "communityId": communityId,
        }
    ).then((response,) {
//      print(response.body);
//      print(jsonDecode(response.body));
      List responseJson = json.decode(response.body);
      List<forumTabs> mytabs = responseJson.map((m) => new forumTabs.fromJson(m)).toList();
      net.onAllTabsResponse(mytabs);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }
  getPostListByTag(NetListener net,String communityId,String tag,String page,String size,String session) async{
    var client = new http.Client();
    client.post(
        getPostByTabs,
        headers: {
          "cookie": session,
        },
        body: {
          "communityId": communityId,
          "tag":tag,
          "page": page,
          "size": size,
        }
    ).then((
        response,
        ) {
      //print(response.body);
      //print(jsonDecode(response.body));
      List responseJson = json.decode(response.body);
      List<Post> post = responseJson.map((m) => new Post.fromJson(m)).toList();
      net.onAllPostResponse(post);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }
}

/**
 * 用来回调成功和失败的结果
 */
abstract class NetListener {



  void onAllPostResponse(List<Post> body);
  void onAllCommentsResponse(List<Comment> commentList);
  void onAddComment(bool success);
  void onDeleteComment(bool success);
  void onError(error);
  void onAllTabsResponse(List<forumTabs> body);
}



class Post{
  final String id;
  final String posterName;
  final String title;
  final String postContent;
  final String postTime;
  final String communityId;
  final List photo;


  Post({
    this.id,
    this.title,
    this.communityId,
    this.postContent,
    this.photo,
    this.posterName,
    this.postTime
  }) ;

  factory Post.fromJson(Map<String, dynamic> json){
    List photo1 =new List();
    if(json['photo']!=null){
      photo1=json['photo'];
    }
    else{
       photo1 = null;
    }
    return new Post(
      id: json['id'].toString(),
      posterName: json['posterName'],
      postTime: json['postTime'],
      postContent: json['postContent'],
      title: json['title'],
      communityId: json['communityId'].toString(),
      photo: photo1,

      //photo: null,
    );
  }
}
class forumTabs{
  final String tag;
  final String num;
  forumTabs({
    this.tag,
    this.num,
});
  factory forumTabs.fromJson(Map<String, dynamic> json){

    return new forumTabs(
      tag: json['tag'],
      num: json['num'].toString(),

    );
  }
}
class Comment{
  final String pid;
  final String commenterName;
  final String postComment;
  final String commentsTime;
  final String location;



  Comment({
    this.pid,
    this.postComment,
    this.commenterName,
    this.commentsTime,
    this.location,
  }) ;

  factory Comment.fromJson(Map<String, dynamic> json){
    return new Comment(
      pid: json['pid'].toString(),
      postComment: json['postComment'],
      commentsTime: json['commentsTime'],
      commenterName: json['commenterName'],
      location: json['location'].toString(),
    );
  }
}






