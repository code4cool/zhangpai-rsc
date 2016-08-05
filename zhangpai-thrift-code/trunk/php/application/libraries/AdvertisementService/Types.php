<?php
namespace application\libraries\AdvertisementService;

/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
use Thrift\Base\TBase;
use Thrift\Type\TType;
use Thrift\Type\TMessageType;
use Thrift\Exception\TException;
use Thrift\Exception\TProtocolException;
use Thrift\Protocol\TProtocol;
use Thrift\Protocol\TBinaryProtocolAccelerated;
use Thrift\Exception\TApplicationException;


class Advertisement {
  static $_TSPEC;

  public $id = null;
  public $adTitle = null;
  public $adDescription = null;
  public $createTime = null;
  public $adLocation = null;
  public $ipSingleNum = null;
  public $ipMuchNum = null;
  public $onlineStatus = null;
  public $auditStatus = null;
  public $adImage = null;
  public $adLink = null;
  public $adContent = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'id',
          'type' => TType::I32,
          ),
        2 => array(
          'var' => 'adTitle',
          'type' => TType::STRING,
          ),
        3 => array(
          'var' => 'adDescription',
          'type' => TType::STRING,
          ),
        4 => array(
          'var' => 'createTime',
          'type' => TType::STRING,
          ),
        5 => array(
          'var' => 'adLocation',
          'type' => TType::I32,
          ),
        6 => array(
          'var' => 'ipSingleNum',
          'type' => TType::I32,
          ),
        7 => array(
          'var' => 'ipMuchNum',
          'type' => TType::I32,
          ),
        8 => array(
          'var' => 'onlineStatus',
          'type' => TType::I32,
          ),
        9 => array(
          'var' => 'auditStatus',
          'type' => TType::I32,
          ),
        10 => array(
          'var' => 'adImage',
          'type' => TType::STRING,
          ),
        11 => array(
          'var' => 'adLink',
          'type' => TType::STRING,
          ),
        12 => array(
          'var' => 'adContent',
          'type' => TType::STRING,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['id'])) {
        $this->id = $vals['id'];
      }
      if (isset($vals['adTitle'])) {
        $this->adTitle = $vals['adTitle'];
      }
      if (isset($vals['adDescription'])) {
        $this->adDescription = $vals['adDescription'];
      }
      if (isset($vals['createTime'])) {
        $this->createTime = $vals['createTime'];
      }
      if (isset($vals['adLocation'])) {
        $this->adLocation = $vals['adLocation'];
      }
      if (isset($vals['ipSingleNum'])) {
        $this->ipSingleNum = $vals['ipSingleNum'];
      }
      if (isset($vals['ipMuchNum'])) {
        $this->ipMuchNum = $vals['ipMuchNum'];
      }
      if (isset($vals['onlineStatus'])) {
        $this->onlineStatus = $vals['onlineStatus'];
      }
      if (isset($vals['auditStatus'])) {
        $this->auditStatus = $vals['auditStatus'];
      }
      if (isset($vals['adImage'])) {
        $this->adImage = $vals['adImage'];
      }
      if (isset($vals['adLink'])) {
        $this->adLink = $vals['adLink'];
      }
      if (isset($vals['adContent'])) {
        $this->adContent = $vals['adContent'];
      }
    }
  }

  public function getName() {
    return 'Advertisement';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->id);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 2:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->adTitle);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 3:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->adDescription);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 4:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->createTime);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 5:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->adLocation);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 6:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->ipSingleNum);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 7:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->ipMuchNum);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 8:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->onlineStatus);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 9:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->auditStatus);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 10:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->adImage);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 11:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->adLink);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 12:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->adContent);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('Advertisement');
    if ($this->id !== null) {
      $xfer += $output->writeFieldBegin('id', TType::I32, 1);
      $xfer += $output->writeI32($this->id);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->adTitle !== null) {
      $xfer += $output->writeFieldBegin('adTitle', TType::STRING, 2);
      $xfer += $output->writeString($this->adTitle);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->adDescription !== null) {
      $xfer += $output->writeFieldBegin('adDescription', TType::STRING, 3);
      $xfer += $output->writeString($this->adDescription);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->createTime !== null) {
      $xfer += $output->writeFieldBegin('createTime', TType::STRING, 4);
      $xfer += $output->writeString($this->createTime);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->adLocation !== null) {
      $xfer += $output->writeFieldBegin('adLocation', TType::I32, 5);
      $xfer += $output->writeI32($this->adLocation);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->ipSingleNum !== null) {
      $xfer += $output->writeFieldBegin('ipSingleNum', TType::I32, 6);
      $xfer += $output->writeI32($this->ipSingleNum);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->ipMuchNum !== null) {
      $xfer += $output->writeFieldBegin('ipMuchNum', TType::I32, 7);
      $xfer += $output->writeI32($this->ipMuchNum);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->onlineStatus !== null) {
      $xfer += $output->writeFieldBegin('onlineStatus', TType::I32, 8);
      $xfer += $output->writeI32($this->onlineStatus);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->auditStatus !== null) {
      $xfer += $output->writeFieldBegin('auditStatus', TType::I32, 9);
      $xfer += $output->writeI32($this->auditStatus);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->adImage !== null) {
      $xfer += $output->writeFieldBegin('adImage', TType::STRING, 10);
      $xfer += $output->writeString($this->adImage);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->adLink !== null) {
      $xfer += $output->writeFieldBegin('adLink', TType::STRING, 11);
      $xfer += $output->writeString($this->adLink);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->adContent !== null) {
      $xfer += $output->writeFieldBegin('adContent', TType::STRING, 12);
      $xfer += $output->writeString($this->adContent);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

class GetAdvertisementInput {
  static $_TSPEC;

  public $id = null;
  public $version = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'id',
          'type' => TType::I32,
          ),
        2 => array(
          'var' => 'version',
          'type' => TType::STRING,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['id'])) {
        $this->id = $vals['id'];
      }
      if (isset($vals['version'])) {
        $this->version = $vals['version'];
      }
    }
  }

  public function getName() {
    return 'GetAdvertisementInput';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->id);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 2:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->version);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('GetAdvertisementInput');
    if ($this->id !== null) {
      $xfer += $output->writeFieldBegin('id', TType::I32, 1);
      $xfer += $output->writeI32($this->id);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->version !== null) {
      $xfer += $output->writeFieldBegin('version', TType::STRING, 2);
      $xfer += $output->writeString($this->version);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

class GetAdvertisementOutput {
  static $_TSPEC;

  public $returnCode = null;
  public $advertisement = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'returnCode',
          'type' => TType::I32,
          ),
        2 => array(
          'var' => 'advertisement',
          'type' => TType::STRUCT,
          'class' => '\application\libraries\AdvertisementService\Advertisement',
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['returnCode'])) {
        $this->returnCode = $vals['returnCode'];
      }
      if (isset($vals['advertisement'])) {
        $this->advertisement = $vals['advertisement'];
      }
    }
  }

  public function getName() {
    return 'GetAdvertisementOutput';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->returnCode);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 2:
          if ($ftype == TType::STRUCT) {
            $this->advertisement = new \application\libraries\AdvertisementService\Advertisement();
            $xfer += $this->advertisement->read($input);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('GetAdvertisementOutput');
    if ($this->returnCode !== null) {
      $xfer += $output->writeFieldBegin('returnCode', TType::I32, 1);
      $xfer += $output->writeI32($this->returnCode);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->advertisement !== null) {
      if (!is_object($this->advertisement)) {
        throw new TProtocolException('Bad type in structure.', TProtocolException::INVALID_DATA);
      }
      $xfer += $output->writeFieldBegin('advertisement', TType::STRUCT, 2);
      $xfer += $this->advertisement->write($output);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

class GetAdvertisementShowOutput {
  static $_TSPEC;

  public $returnCode = null;
  public $id = null;
  public $adTitle = null;
  public $adDescription = null;
  public $createTime = null;
  public $adLocation = null;
  public $ipSingleNum = null;
  public $ipMuchNum = null;
  public $onlineStatus = null;
  public $auditStatus = null;
  public $adImage = null;
  public $adLink = null;
  public $adContent = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'returnCode',
          'type' => TType::I32,
          ),
        2 => array(
          'var' => 'id',
          'type' => TType::I32,
          ),
        3 => array(
          'var' => 'adTitle',
          'type' => TType::STRING,
          ),
        4 => array(
          'var' => 'adDescription',
          'type' => TType::STRING,
          ),
        5 => array(
          'var' => 'createTime',
          'type' => TType::STRING,
          ),
        6 => array(
          'var' => 'adLocation',
          'type' => TType::I32,
          ),
        7 => array(
          'var' => 'ipSingleNum',
          'type' => TType::I32,
          ),
        8 => array(
          'var' => 'ipMuchNum',
          'type' => TType::I32,
          ),
        9 => array(
          'var' => 'onlineStatus',
          'type' => TType::I32,
          ),
        10 => array(
          'var' => 'auditStatus',
          'type' => TType::I32,
          ),
        11 => array(
          'var' => 'adImage',
          'type' => TType::STRING,
          ),
        12 => array(
          'var' => 'adLink',
          'type' => TType::STRING,
          ),
        13 => array(
          'var' => 'adContent',
          'type' => TType::STRING,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['returnCode'])) {
        $this->returnCode = $vals['returnCode'];
      }
      if (isset($vals['id'])) {
        $this->id = $vals['id'];
      }
      if (isset($vals['adTitle'])) {
        $this->adTitle = $vals['adTitle'];
      }
      if (isset($vals['adDescription'])) {
        $this->adDescription = $vals['adDescription'];
      }
      if (isset($vals['createTime'])) {
        $this->createTime = $vals['createTime'];
      }
      if (isset($vals['adLocation'])) {
        $this->adLocation = $vals['adLocation'];
      }
      if (isset($vals['ipSingleNum'])) {
        $this->ipSingleNum = $vals['ipSingleNum'];
      }
      if (isset($vals['ipMuchNum'])) {
        $this->ipMuchNum = $vals['ipMuchNum'];
      }
      if (isset($vals['onlineStatus'])) {
        $this->onlineStatus = $vals['onlineStatus'];
      }
      if (isset($vals['auditStatus'])) {
        $this->auditStatus = $vals['auditStatus'];
      }
      if (isset($vals['adImage'])) {
        $this->adImage = $vals['adImage'];
      }
      if (isset($vals['adLink'])) {
        $this->adLink = $vals['adLink'];
      }
      if (isset($vals['adContent'])) {
        $this->adContent = $vals['adContent'];
      }
    }
  }

  public function getName() {
    return 'GetAdvertisementShowOutput';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->returnCode);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 2:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->id);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 3:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->adTitle);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 4:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->adDescription);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 5:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->createTime);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 6:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->adLocation);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 7:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->ipSingleNum);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 8:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->ipMuchNum);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 9:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->onlineStatus);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 10:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->auditStatus);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 11:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->adImage);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 12:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->adLink);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 13:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->adContent);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('GetAdvertisementShowOutput');
    if ($this->returnCode !== null) {
      $xfer += $output->writeFieldBegin('returnCode', TType::I32, 1);
      $xfer += $output->writeI32($this->returnCode);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->id !== null) {
      $xfer += $output->writeFieldBegin('id', TType::I32, 2);
      $xfer += $output->writeI32($this->id);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->adTitle !== null) {
      $xfer += $output->writeFieldBegin('adTitle', TType::STRING, 3);
      $xfer += $output->writeString($this->adTitle);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->adDescription !== null) {
      $xfer += $output->writeFieldBegin('adDescription', TType::STRING, 4);
      $xfer += $output->writeString($this->adDescription);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->createTime !== null) {
      $xfer += $output->writeFieldBegin('createTime', TType::STRING, 5);
      $xfer += $output->writeString($this->createTime);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->adLocation !== null) {
      $xfer += $output->writeFieldBegin('adLocation', TType::I32, 6);
      $xfer += $output->writeI32($this->adLocation);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->ipSingleNum !== null) {
      $xfer += $output->writeFieldBegin('ipSingleNum', TType::I32, 7);
      $xfer += $output->writeI32($this->ipSingleNum);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->ipMuchNum !== null) {
      $xfer += $output->writeFieldBegin('ipMuchNum', TType::I32, 8);
      $xfer += $output->writeI32($this->ipMuchNum);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->onlineStatus !== null) {
      $xfer += $output->writeFieldBegin('onlineStatus', TType::I32, 9);
      $xfer += $output->writeI32($this->onlineStatus);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->auditStatus !== null) {
      $xfer += $output->writeFieldBegin('auditStatus', TType::I32, 10);
      $xfer += $output->writeI32($this->auditStatus);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->adImage !== null) {
      $xfer += $output->writeFieldBegin('adImage', TType::STRING, 11);
      $xfer += $output->writeString($this->adImage);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->adLink !== null) {
      $xfer += $output->writeFieldBegin('adLink', TType::STRING, 12);
      $xfer += $output->writeString($this->adLink);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->adContent !== null) {
      $xfer += $output->writeFieldBegin('adContent', TType::STRING, 13);
      $xfer += $output->writeString($this->adContent);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

