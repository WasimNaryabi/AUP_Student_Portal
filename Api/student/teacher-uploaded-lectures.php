<?php
include_once '../config/db_connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function AssignmentDetails($course_code)
		{
			 
		  $i= 1;
		 
	 
$Lecture['lectures']=array();
		$select_courses =mysqli_query($this->connection,"SELECT * FROM all_lectures WHERE course_id = '$course_code'");
  while($data = mysqli_fetch_array($select_courses)){ 
                              $topic  =  $data['topic'];
                              $path = $data['files_path'];
                              $date = $data['date'];
							   
							  $response=array(
					         	'Topic'=>$topic,
						        'Date'=>$date,
								'Path' =>$path,
								'LectureNo'=>'Lecture No '.$i
			 );
			 array_push($Lecture['lectures'],$response);
                             
$i++;
                             }
				 
				echo json_encode($Lecture);
				mysqli_close($this -> connection);
			 
			
		}
	}
		
	 
	
	$user = new User();
	if(isset($_POST['course_code'])) {
		 
		 
		$course_code = $_POST['course_code'];
		 
	 

		//echo $discipline_id."  /  ".$session_id."  /  ".$semester_id."  /  ".$section_id."  /  ".$subject_id;exit();
		if(!empty($course_code)){
			
		 
			$user-> AssignmentDetails($course_code);
			
		}else{
			echo json_encode("No Subject  Found");
		}
		
	}
