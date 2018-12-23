# DropBoxAssignment


This application interacts with DropBox's REST Api's to do various functionalities like Getting files, Downloading and uploading files.


It includes, 
1) `/getfiles` which returns metadata of all the files existing in the dropBox home folder.

2) `/download/{fileName}` which takes in file name to be downloaded from the user as pathVariable.
    and handles exception when specified file doesnot exists in dropBox.

3) `/upload/{fileName}` which takes in the file name to be uploaded from downloads folder from the host machine(mac) and handles exception when specified file doesnot exists in dropBox.

4) '/upload/{folderName}/{fileName}' which takes in the file name to be uploaded from downloads folder from the host machine(mac) and uploads to a specific folder in the dropBox given by the user  and handles exception when specified file doesnot exists in dropBox. 
