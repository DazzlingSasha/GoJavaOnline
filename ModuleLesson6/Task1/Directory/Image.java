package ModuleLesson6.Task1.Directory;


class Image extends File{
    private String puth = "c://img.jpeg";
    @Override
    public String getNameFile() {
        return puth;
    }
}
