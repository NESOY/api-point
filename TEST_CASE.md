### 3 point
```json
{
 "type": "REVIEW",
 "action": "ADD",
 "reviewId": "CONTENT_2_PHOTO_FIRST_REVIEW",
 "content": "NESOY",
 "attachedPhotoIds": ["PHOTO_1", "PHOTO_2"],
 "userId": "NESOY",
 "placeId": "SEOUL"
}
```

### 0 point
```json
{
 "type": "REVIEW",
 "action": "ADD",
 "reviewId": "NO_CONTENT_NO_PHOTO_NOT_FIRST_REVIEW",
 "content": "",
 "attachedPhotoIds": [],
 "userId": "NESOY",
 "placeId": "SEOUL"
}
```

### 1 point
```json
{
 "type": "REVIEW",
 "action": "ADD",
 "reviewId": "INCLUE_CONTENT_NO_PHOTO_NOT_FIRST_REVIEW",
 "content": "CONTENT",
 "attachedPhotoIds": [],
 "userId": "NESOY",
 "placeId": "SEOUL"
}
```

### 1 point
```json
{
 "type": "REVIEW",
 "action": "ADD",
 "reviewId": "NO_CONTENT_ONLY_PHOTO_NOT_FIRST_REVIEW",
 "content": "",
 "attachedPhotoIds": ["PHOTO_3"],
 "userId": "NESOY",
 "placeId": "SEOUL"
}
```

### Remove Process
```json
{
 "type": "REVIEW",
 "action": "DELETE",
 "reviewId": "DELETED_REVIEW",
 "content": "CONTENT",
 "attachedPhotoIds": [""],
 "userId": "NESOY",
 "placeId": "BUSAN"
}
```