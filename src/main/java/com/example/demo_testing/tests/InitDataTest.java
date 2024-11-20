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
        User user = new User("Sinh");

        Assert.assertNotNull(user.getStores());
    }

    @Test
    public void initDriveDataTest() {
        Drive drive = new Drive("Google Drive", new User("Sinh"));

        Assert.assertTrue(drive.getChildStores().isEmpty());
        Assert.assertNotNull(drive.getPermissions());
    }

    @Test
    public void initFolderDataTest() {
        Folder folder = new Folder("Design Documents", new User("Sinh"));

        Assert.assertNotNull(folder.getChildStores());
        Assert.assertNotNull(folder.getPermissions());
    }

    @Test
    public void initFileDataTest() {
        File file = new File("Marketing Materials.txt", new User("Sinh"), "Content");

        Assert.assertNotNull(file.getPermissions());
    }
}