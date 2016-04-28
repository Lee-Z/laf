/*
 Copyright (c) 2013 Katsuma Tanaka
 
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

#import <UIKit/UIKit.h>
#import <AssetsLibrary/AssetsLibrary.h>

// Delegate
#import "QBAssetCollectionViewControllerDelegate.h"

typedef enum {
    QBImagePickerFilterTypeAllAssets,
    QBImagePickerFilterTypeAllPhotos,
    QBImagePickerFilterTypeAllVideos
} QBImagePickerFilterType;

extern NSString const *UIImagePickerControllerThunbnailImage;

@class QBImagePickerController;

@protocol QBImagePickerControllerDelegate <NSObject>

@optional
- (void)imagePickerControllerWillFinishPickingMedia:(QBImagePickerController *)imagePickerController;
- (void)imagePickerController:(QBImagePickerController *)imagePickerController didFinishPickingMediaWithInfos:(NSArray<NSDictionary *> *)infos;
- (void)imagePickerControllerDidCancel:(QBImagePickerController *)imagePickerController;

@end


@interface QBImagePickerController : UIViewController <UITableViewDataSource, UITableViewDelegate, QBAssetCollectionViewControllerDelegate>

@property (nonatomic, assign) id<QBImagePickerControllerDelegate> delegate;
@property (nonatomic, assign) QBImagePickerFilterType filterType;
@property (nonatomic, assign) BOOL showCancelButton;
@property (nonatomic, assign) BOOL showToolbar;

@property (nonatomic, assign) BOOL allowsMultipleSelection;
@property (nonatomic, assign) BOOL limitsMinimumNumberOfSelection;
@property (nonatomic, assign) BOOL limitsMaximumNumberOfSelection;
@property (nonatomic, assign) NSUInteger minimumNumberOfSelection;
@property (nonatomic, assign) NSUInteger maximumNumberOfSelection;

@property(nonatomic, assign) UIBarStyle navigationBarStyle;
@property(nonatomic, assign) BOOL navigationBarTranslucent;
@property(nonatomic, strong) UIColor *navigationBarTintColor;
@property(nonatomic, strong) UIColor *navigationBarBarTintColor;
@property(nonatomic, strong) UIImage *navigationBarBackgroundImage;
@property(nonatomic, strong) UIImage *navigationBarShadowImage;
@property(nonatomic, assign) UIStatusBarStyle statusBarStyle;

@end
