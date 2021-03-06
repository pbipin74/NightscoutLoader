package davidRichardson;

public class FileChecker 
{

	// Opens supplied file and checks validity against known types...
	enum FileCheckType
	{
		INVALID,

		Medtronic,
		Diasend,
		OmniPod,
		RocheSQLExtract,
	}

	public static String getFileTypeStr(FileCheckType fct)
	{
		String result = new String("");

		switch (fct)
		{
		case INVALID : result = "INVALID"; break;
		case Medtronic : result = "Medtronic"; break;
		case Diasend : result = "Diasend"; break;
		case OmniPod : result = "OmniPod"; break;
		case RocheSQLExtract : result = "Roche SQL Extract"; break;

		default : result = "UNKNOWN"; break;
		}

		return result;
	}

	public static FileCheckType checkFile(String filename)
	{
		FileCheckType result = FileChecker.FileCheckType.INVALID;

		// Protect these checks by file name type
		if (filename.contains(".xls"))
		{
			if (isDiasend(filename))
			{
				result = FileChecker.FileCheckType.Diasend;
			}
		}
		else if (filename.contains(".csv"))
		{
			if (isMedtronic(filename))
			{
				result = FileChecker.FileCheckType.Medtronic;
			}
			else if (isRocheCSV(filename))
			{
				result = FileChecker.FileCheckType.RocheSQLExtract;
			}
		}
		else if (filename.contains(".ibf") && isOmniPod(filename))
		{
			result = FileChecker.FileCheckType.OmniPod;
		}

		return result;
	}

	public static FileCheckType checkBinaryFile(String filename)
	{
		FileCheckType result = FileChecker.FileCheckType.INVALID;

		if (isMedtronic(filename))
		{
			result = FileChecker.FileCheckType.Medtronic;
		}
		else if (isDiasend(filename))
		{
			result = FileChecker.FileCheckType.Diasend;
		}
		else if (isDiasend(filename))
		{
			result = FileChecker.FileCheckType.Diasend;
		}

		return result;
	}


	private static boolean isMedtronic(String fileName)
	{
		boolean result = DataLoadMedtronic.isMedtronic(fileName);
		return result;
	}

	private static boolean isDiasend(String fileName)
	{
		boolean result = DataLoadDiasend.isDiasend(fileName);
		return result;
	}
	private static boolean isOmniPod(String fileName)
	{
		boolean result = DataLoadOmniPod.isOmniPod(fileName);
		return result;
	}

	private static boolean isRocheCSV(String fileName)
	{
		boolean result = DataLoadRocheCSV.isRoche(fileName);
		return result;
	}

	
}
