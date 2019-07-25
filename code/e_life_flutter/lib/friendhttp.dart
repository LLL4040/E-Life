import 'dart:convert';

import 'package:http/http.dart' as http;
class friendHttp {

  var friendUrl = "http://elife.natapp1.cc/user-server/api/friend/friendList";

  var friendSearchListUrl = "http://elife.natapp1.cc/user-server/api/friend/friendSearchList";

  var requestListUrl = "http://elife.natapp1.cc/user-server/api/friend/responseList";

  var responseListUrl= "http://elife.natapp1.cc/user-server/api/friend/requestList";

  var sendRequestUrl= "http://elife.natapp1.cc/user-server/api/friend/sendFriendRequest";

  var acceptRequestUrl= "http://elife.natapp1.cc/user-server/api/friend/acceptRequest";

  var rejectRequestUrl= "http://elife.natapp1.cc/user-server/api/friend/rejectRequest";

  var deleteFriendUrl= "http://elife.natapp1.cc/user-server/api/friend/deleteFriend";


  /**
   * get friend list
   * @param username
   * @return friend list
   */
  getFriendList(NetListener net,String username) {
    var client = new http.Client();
    client.post(
        friendUrl,
        body: {
          "username": username,
        }
    ).then((
        response,
        ) {
      print(response.body);
      print(jsonDecode(response.body));
      List responseJson = json.decode(response.body);
      List<Friend> friendList = responseJson.map((m) => new Friend.fromJson(m)).toList();
      net.onFriendResponse(friendList);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  getFriendSearchList(NetListener net,String username) {
    var client = new http.Client();
    client.post(
        friendSearchListUrl,
        body: {
          "username": username,
        }
    ).then((
        response,
        ) {
      print(response.body);
      print(jsonDecode(response.body));
      List responseJson = json.decode(response.body);
      List<Friend> friendList = responseJson.map((m) => new Friend.fromJson(m)).toList();
      net.onFriendSearchResponse(friendList);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  getRequestList(NetListener net,String username) {
    var client = new http.Client();
    client.post(
        requestListUrl,
        body: {
          "username": username,
        }
    ).then((
        response,
        ) {
      print(response.body);
      print(jsonDecode(response.body));
      List responseJson = json.decode(response.body);
      List<friendRequest> requestList = responseJson.map((m) => new friendRequest.fromJson(m)).toList();
      net.onRequestListResponse(requestList);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  getResponseList(NetListener net,String username) {
    var client = new http.Client();
    client.post(
        responseListUrl,
        body: {
          "username": username,
        }
    ).then((
        response,
        ) {
      print(response.body);
      print(jsonDecode(response.body));
      List responseJson = json.decode(response.body);
      List<friendRequest> responseList = responseJson.map((m) => new friendRequest.fromJson(m)).toList();
      net.onResponseListResponse(responseList);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  sendRequest(NetListener net,String username,String friend,String content) {
    var client = new http.Client();
    client.post(
        sendRequestUrl,
        body: {
          "username": username,
          "friend": friend,
          "content": content,
        }
    ).then((
        response,
        ) {
      Map<String,dynamic> responseJson = json.decode(response.body);
      bool success = responseJson.containsValue("1");
      net.onSendRequestResponse(success);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  acceptRequest(NetListener net,int id) {
    var client = new http.Client();
    client.post(
        acceptRequestUrl,
        body: {
          "id": id.toString(),
        }
    ).then((
        response,
        ) {
      Map<String,dynamic> responseJson = json.decode(response.body);
      print("请求加好友成功");
      bool success = responseJson.containsValue("1");
      net.onAcceptRequestResponse(success);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  rejectRequest(NetListener net,int id) {
    var client = new http.Client();
    client.post(
        rejectRequestUrl,
        body: {
          "id": id.toString(),
        }
    ).then((
        response,
        ) {
      Map<String,dynamic> responseJson = json.decode(response.body);
      bool success = responseJson.containsValue("1");
      net.onRejectRequestResponse(success);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  deleteFriend(NetListener net,String username,String friend) {
    var client = new http.Client();
    client.post(
        deleteFriendUrl,
        body: {
          "username": username,
          "friend":friend,
        }
    ).then((
        response,
        ) {
      print(response);
      Map<String,dynamic> responseJson = json.decode(response.body);
      bool success = responseJson.containsValue("1");
      net.onDeleteFriendResponse(success);
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

  void onFriendResponse(List<Friend> friendList);
  void onFriendSearchResponse(List<Friend> friendSearchList);
  void onRequestListResponse(List<friendRequest> requestList);
  void onResponseListResponse(List<friendRequest> responseList);
  void onSendRequestResponse(bool send);
  void onAcceptRequestResponse(bool accept);
  void onRejectRequestResponse(bool reject);
  void onDeleteFriendResponse(bool delete);
  void onError(error);
}

class Friend{
  final String friend;
  final String phone;
  final String email;
  final String communityId;
  final String searchUser;

  Friend({
    this.friend,
    this.phone,
    this.email,
    this.communityId,
    this.searchUser
  }) ;

  factory Friend.fromJson(Map<String, dynamic> json){
    return new Friend(
      friend: json['friend'],
      phone: json['phone'],
      email: json['email'],
      communityId: json['community'],
      searchUser: json['username'],
    );
  }
}

class friendRequest{
  final int id;
  final String request;
  final String content;
  final int status;

  friendRequest({
    this.id,
    this.request,
    this.content,
    this.status,
  }) ;
  factory friendRequest.fromJson(Map<String, dynamic> json){
    return new friendRequest(
      id: json['id'],
      request: json['request'],
      content: json['content'],
      status: json['status'],
    );
  }
}




