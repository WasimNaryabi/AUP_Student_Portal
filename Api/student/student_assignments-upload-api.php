<?php
include_once '../config/db_connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function AssignmentDetails($student_id)
		{
			$response = array();
			 $query_select1 = mysqli_query($this->connection,"select * from student as std, session as ses where std.student_id = '$id' and ses.session_id = std.session_id;");
	 
	$row = mysqli_fetch_array($query_select1);
	 
	$discipline = $row['discipline_id'];
	$session_id = $row['session_id'];

	$query_student_history1 = $pdo->prepare("select * from student_history where student_id = '$id' and semester_id in(select max(semester_id) from student_history 
							  where student_id = '$id');");
	$query_student_history = mysqli_fetch_array($query_student_history1);
 
	 
		
	$student_history_id = $query_student_history['student_history_id'];
	$semester = $query_student_history['semester_id'];
		
	$login_date = date("d/m/Y");


 $i= 1;

 
                             
                              


				$response[$i]=array(
						
						'course_name'=>$course_name,
						'date'=>$date,
						'topic'=>$topic
						 

			 
			 );
			 
				 
				echo json_encode($response);
				mysqli_close($this -> connection);
			 
			
		}
		
	}
	
	$user = new User();
	if(isset($_POST['faculty_id'],$_POST['discipline_id'],$_POST['session_id'],$_POST['semester_id'],$_POST['subject_id'])) {
		 
		$faculty_id = $_POST['faculty_id'];
		$discipline_id = $_POST['discipline_id'];
		$session_id = $_POST['session_id'];
		$semester_id = $_POST['semester_id'];
		 
		$subject_id = $_POST['subject_id'];

		//echo $discipline_id."  /  ".$session_id."  /  ".$semester_id."  /  ".$section_id."  /  ".$subject_id;exit();
		if(!empty($faculty_id) && !empty($discipline_id) && !empty($session_id) && !empty($semester_id) &&  !empty($subject_id)){
			
		 
			$user-> AssignmentDetails($faculty_id,$discipline_id,$session_id,$semester_id,$subject_id);
			
		}else{
			echo json_encode("No Classes Found");
		}
		
	}
