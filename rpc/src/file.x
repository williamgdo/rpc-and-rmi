
struct file_in{
	string file_name<>;
	opaque buffer[4098];
};

program FILE_PROG {
	version FILE_VERS {
		int FILECREATE(file_in) = 1;
		int FILESEND(file_in) = 2;
	} = 1;
} = 0x31230000;
