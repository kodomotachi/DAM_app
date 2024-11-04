package tests;

import org.junit.Assert;
import org.junit.Test;

import models.Drive;
import models.File;
import models.Folder;
import models.User;

public class InitDataTest {
    
    @Test
    public void initUserDataTest() {
        User user = new User("sinhdangphuoctruong@gmail.com", "sinh270704");

        Assert.assertNull(user.getDrives());
    }

    @Test
    public void initDriveDataTest() {
        Drive drive = new Drive("Google Drive");

        Assert.assertNotNull(drive.getChildStores());
        Assert.assertNotNull(drive.getPermissions());
    }

    @Test
    public void initFolderDataTest() {
        Folder folder = new Folder("Design Documents");

        Assert.assertNotNull(folder.getChildStores());
        Assert.assertNotNull(folder.getPermissions());
    }

    @Test
    public void initFileDataTest() {
        File file = new File("Marketing Materials.pdf");

        Assert.assertNotNull(file.getPermissions());
    }
}
